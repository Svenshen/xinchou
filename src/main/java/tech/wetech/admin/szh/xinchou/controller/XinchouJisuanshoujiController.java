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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.wetech.admin.core.annotation.SystemLog;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.core.utils.ResultCodeEnum;
import tech.wetech.admin.modules.system.po.Organization;
import tech.wetech.admin.modules.system.service.OrganizationService;
import tech.wetech.admin.szh.xinchou.domain.Shoujidanjiaticheng;
import tech.wetech.admin.szh.xinchou.domain.ShoujidanjiatichengId;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehu;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehuleibie;
import tech.wetech.admin.szh.xinchou.domain.Shoujileibie;
import tech.wetech.admin.szh.xinchou.domain.Xinchoushuju;
import tech.wetech.admin.szh.xinchou.service.ShoujidanjiatichengService;
import tech.wetech.admin.szh.xinchou.service.ShoujikehuService;
import tech.wetech.admin.szh.xinchou.service.ShoujikehuleibieService;
import tech.wetech.admin.szh.xinchou.service.ShoujileibieService;
import tech.wetech.admin.szh.xinchou.service.ShoujishujuService;
import tech.wetech.admin.szh.xinchou.service.XinchoufanganService;
import tech.wetech.admin.szh.xinchou.service.XinchoushujuService;
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
    @Autowired
    XinchoushujuService xinchoushujuService;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
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
    @PostMapping("/baocun")
    @RequiresPermissions("jisuan:shoujibaocun")
    @SystemLog("收寄薪酬另存")
    public Result baocun(@RequestParam(name = "kshijian") String kshijian,@RequestParam(name = "jshijian") String jshijian,@RequestParam(name = "fangan") String fangan) throws  ParseException{
        
        if(kshijian == null || jshijian == null || fangan == null){
            return Result.failure(ResultCodeEnum.BAD_REQUEST);
        }
        Long fanganid = Long.valueOf(fangan);
        
        xinchoushujuService.baocunshoujixinchou(fanganid, sdf.parse(kshijian), sdf.parse(jshijian));
        return Result.success();
    }
    
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("jisuan:shouji")
    public Result<List<ShoujixinchouVO>> queryList(HttpServletRequest request) throws  ParseException{
        String kshijian = request.getParameter("kshijian");
        String jshijian = request.getParameter("jshijian");
        if(kshijian == null || jshijian == null){
            return Result.failure(ResultCodeEnum.BAD_REQUEST);
        }
        
        return Result.success(xinchoushujuService.jisuanshoujixinchou(sdf.parse(kshijian), sdf.parse(jshijian)));
    }
    
    
    
    public String getOrganizationName(Long organizationId) {
        if(organizationId == null){
            return "";
        }
        Organization organization = organizationService.queryOne(new Organization().setId(organizationId));
        if (organization == null) {
            return "";
        }
        return organization.getName();
    }
    
    public String getYewuName(Long yewuid) {
        if(yewuid == null){
            return "未知业务1";
        }
        Shoujileibie shoujileibie = shoujileibieService.queryById(yewuid);
        if (shoujileibie == null) {
            return "未知业务2";
        }
        return shoujileibie.getName();
    }
    
    public String getKehu(Long kehuid){
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
