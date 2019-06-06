/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.modules.system.po.Organization;
import tech.wetech.admin.modules.system.service.OrganizationService;
import tech.wetech.admin.szh.xinchou.domain.Shoujidanjiaticheng;
import tech.wetech.admin.szh.xinchou.domain.ShoujidanjiatichengId;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehu;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehuleibie;
import tech.wetech.admin.szh.xinchou.domain.Shoujileibie;
import tech.wetech.admin.szh.xinchou.service.ShoujidanjiatichengService;
import tech.wetech.admin.szh.xinchou.service.ShoujikehuService;
import tech.wetech.admin.szh.xinchou.service.ShoujikehuleibieService;
import tech.wetech.admin.szh.xinchou.service.ShoujileibieService;
import tech.wetech.admin.szh.xinchou.service.ShoujishujuService;
import tech.wetech.admin.szh.xinchou.service.XinchoufanganService;
import tech.wetech.admin.szh.xinchou.vo.ShoujixinchouVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-4 10:34:39
 */
@Controller
@RequestMapping("/xinchou/jisuan/shouji")
public class XinchouJisuanshoujiController {
    
    @Autowired
    XinchoufanganService xinchoufanganService;
    
    @Autowired
    ShoujishujuService shoujishujuService;
    
    @Autowired
    OrganizationService organizationService;
    @Autowired
    ShoujikehuleibieService shoujikehuleibieService;
    @Autowired
    ShoujileibieService shoujileibieService;
    @Autowired
    ShoujidanjiatichengService shoujidanjiatichengService;
    
    @ModelAttribute("fanganList")
    public List fanganlist(){
        return xinchoufanganService.queryAll();
    }
    
    
    @GetMapping
    @RequiresPermissions("jisuan:shouji")
    public String page(){
        return "xinchou/jisuanshouji";
    }
    
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("jisuan:shouji")
    public Result<List<ShoujixinchouVO>> queryList(HttpServletRequest request) throws  ParseException{
        String kshijian = request.getParameter("kshijian");
        String jshijian = request.getParameter("jshijian");
        if(kshijian == null || jshijian == null){
            return Result.success();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<ShoujixinchouVO> shoujixinchouVOs = shoujishujuService.queryxinchoushoujilist(sdf.parse(kshijian), sdf.parse(jshijian));
        List<ShoujixinchouVO> shoujixinchouVOs1 = new ArrayList();
        String xingming = "";
        Long bumen = 0L;
        double xinchouheji = 0.0;
        for(ShoujixinchouVO shoujixinchouvo:shoujixinchouVOs){
            if(xingming.equals("") && bumen == 0L && xinchouheji == 0.0){
                xingming = shoujixinchouvo.getName();
                bumen = shoujixinchouvo.getBumenid();
            }else{
                if(!xingming.equals(shoujixinchouvo.getName()) || bumen != shoujixinchouvo.getBumenid()){
                    ShoujixinchouVO shoujixinchouVO = new ShoujixinchouVO(bumen, getOrganizationName(bumen), xingming, -1L, "合计", -1L, "", 0, 0, 0, 0, xinchouheji);
                    shoujixinchouVOs1.add(shoujixinchouVO);
                    xinchouheji = 0.0;
                }
            }
            ShoujidanjiatichengId shoujidanjiatichengId = new ShoujidanjiatichengId();
            shoujidanjiatichengId.setBumenid(shoujixinchouvo.getBumenid());
            
            shoujidanjiatichengId.setKehuid(shoujixinchouvo.getKehuid());
            shoujidanjiatichengId.setYewuid(shoujixinchouvo.getYewuid());
            shoujidanjiatichengId.setName(shoujixinchouvo.getName());
            
            Shoujidanjiaticheng shoujidanjiaticheng = shoujidanjiatichengService.queryById(shoujidanjiatichengId);
            double danjia = 0.0;
            double ticheng = 0.0;
            if(shoujidanjiaticheng != null){
                danjia = shoujidanjiaticheng.getDanjia();
                ticheng = shoujidanjiaticheng.getTicheng();
            }
            double xinchou = 0.0;
            xinchou = shoujixinchouvo.getJianshu()*danjia+shoujixinchouvo.getShouru()*ticheng;
            xinchouheji += xinchou;
            shoujixinchouvo.setBumen(getOrganizationName(shoujixinchouvo.getBumenid()));
            shoujixinchouvo.setYewu(getYewuName(shoujixinchouvo.getYewuid()));
            shoujixinchouvo.setKehu(getKehu(shoujixinchouvo.getKehuid()));
            shoujixinchouvo.setDanjia(danjia);
            shoujixinchouvo.setTicheng(ticheng);
            shoujixinchouvo.setXinchou(xinchou);
            
//            ShoujixinchouVO shoujixinchouVO = new ShoujixinchouVO(toudishuju,  getOrganizationName(toudishuju.getBumenid()), getYewuName(toudishuju.getYewuid()), danjia, xinchou);
            shoujixinchouVOs1.add(shoujixinchouvo);
        }
        return Result.success(shoujixinchouVOs1);
    }
    
    
    
    private String getOrganizationName(Long organizationId) {
        if(organizationId == null){
            return "";
        }
        Organization organization = organizationService.queryOne(new Organization().setId(organizationId));
        if (organization == null) {
            return "";
        }
        return organization.getName();
    }
    
    private String getYewuName(Long yewuid) {
        if(yewuid == null){
            return "未知业务1";
        }
        Shoujileibie shoujileibie = shoujileibieService.queryById(yewuid);
        if (shoujileibie == null) {
            return "未知业务2";
        }
        return shoujileibie.getName();
    }
    
    private String getKehu(Long kehuid){
        if(kehuid == null){
            kehuid = 1L;
        }
        Shoujikehuleibie shoujikehuleibie =shoujikehuleibieService.queryById(kehuid);
        if(shoujikehuleibie == null){
            return "未知客户";
        }
        return shoujikehuleibie.getName();
    }

}
