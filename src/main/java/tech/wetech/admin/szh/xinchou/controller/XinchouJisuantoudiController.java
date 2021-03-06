/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
import tech.wetech.admin.szh.xinchou.domain.Toudidanjia;
import tech.wetech.admin.szh.xinchou.domain.ToudidanjiaId;
import tech.wetech.admin.szh.xinchou.domain.Toudishuju;
import tech.wetech.admin.szh.xinchou.domain.Toudiyewu;
import tech.wetech.admin.szh.xinchou.domain.Xinchoushuju;
import tech.wetech.admin.szh.xinchou.service.ToudidanjiaService;
import tech.wetech.admin.szh.xinchou.service.ToudishujuService;
import tech.wetech.admin.szh.xinchou.service.ToudiyewuService;
import tech.wetech.admin.szh.xinchou.service.XinchoufanganService;
import tech.wetech.admin.szh.xinchou.service.XinchoushujuService;
import tech.wetech.admin.szh.xinchou.vo.ToudixinchouVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-21 13:28:10
 */
@Controller
@RequestMapping("/xinchou/jisuan/toudi")
public class XinchouJisuantoudiController {
    
    @Autowired
    ToudishujuService toudishujuService;
    @Autowired
    ToudiyewuService toudiyewuService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    XinchoufanganService xinchoufanganService;
    @Autowired
    ToudidanjiaService toudidanjiaService;
    @Autowired
    XinchoushujuService xinchoushujuService;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @ModelAttribute("fanganList")
    public List fanganlist(){
        return xinchoufanganService.queryAll();
    }
    
    @GetMapping
    @RequiresPermissions("jisuan:toudi")
    public String page(){
        return "xinchou/jisuantoudi";
    }
    
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("jisuan:toudi")
    public Result<List<ToudixinchouVO>> queryList(HttpServletRequest request) throws  ParseException{
        String kshijian = request.getParameter("kshijian");
        String jshijian = request.getParameter("jshijian");
        if(kshijian == null || jshijian == null){
            return Result.failure(ResultCodeEnum.BAD_REQUEST);
        }
        
        
        return Result.success(xinchoushujuService.jisuantoudixinchou(sdf.parse(kshijian), sdf.parse(jshijian)));
    }
    
    
    @ResponseBody
    @PostMapping("/baocun")
    @RequiresPermissions("jisuan:toudibaocun")
    @SystemLog("投递收薪酬另存")
    public Result<List<ToudixinchouVO>> baocun(@RequestParam(name = "kshijian") String kshijian,@RequestParam(name = "jshijian") String jshijian,@RequestParam(name = "fangan") String fangan) throws  ParseException{
        
        if(kshijian == null || jshijian == null || fangan == null){
            return Result.failure(ResultCodeEnum.BAD_REQUEST);
        }
        Long fanganid = Long.valueOf(fangan);
        System.out.println(fanganid);
        System.out.println(kshijian+jshijian);
        xinchoushujuService.baocuntoudixinchou(fanganid, sdf.parse(kshijian), sdf.parse(jshijian));
        return Result.success();
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
            return "";
        }
        Toudiyewu toudiyewu = toudiyewuService.queryById(yewuid);
        if (toudiyewu == null) {
            return "";
        }
        return toudiyewu.getName();
    }
}
