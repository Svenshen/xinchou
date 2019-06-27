/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.wetech.admin.core.annotation.SystemLog;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.core.utils.ResultCodeEnum;
import tech.wetech.admin.modules.system.dto.TreeDto;
import tech.wetech.admin.modules.system.po.User;
import tech.wetech.admin.modules.system.service.OrganizationService;
import tech.wetech.admin.modules.system.service.UserService;
import tech.wetech.admin.szh.xinchou.domain.Xinchoushuju;
import tech.wetech.admin.szh.xinchou.service.XinchoufanganService;
import tech.wetech.admin.szh.xinchou.service.XinchoushujuService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-11 13:56:28
 */
@Controller
@RequestMapping("/xinchou/chaxun")
public class XinchouController {
    
    @Autowired
    XinchoufanganService xinchoufanganService;
    @Autowired
    XinchoushujuService xinchoushujuService;
    @Autowired
    UserService userService;
    @Autowired
    OrganizationService organizationService;
    
    @ModelAttribute("fanganList")
    public List fanganlist(){
        return xinchoufanganService.queryAll();
    }

    @GetMapping("/bumen")
    @RequiresPermissions("chaxun:bumen")
    public String pagebumen(){
        return "xinchou/chaxunbumen";
    }
    
    @GetMapping("/geren")
    @RequiresPermissions("chaxun:gern")
    public String pagegeren(){
        return "xinchou/chaxungeren";
    }
    
    @ResponseBody
    @GetMapping("/bumen/list")
    @RequiresPermissions("chaxun:bumen")
    //@SystemLog("薪酬查询部门")
    public Result<List<Xinchoushuju>> querybumenList(@RequestParam(name = "fangan") String fangan) {
        if(fangan == null ){
            return Result.failure(ResultCodeEnum.BAD_REQUEST);
        }
        Long fanganid = Long.valueOf(fangan);
        Subject subject = SecurityUtils.getSubject();
        String username = (String)subject.getPrincipal();
        User user = userService.queryOne(new User().setUsername(username));
        return Result.success(xinchoushujuService.bumenchaxun(fanganid, user));
    }
    
        
    @ResponseBody
    @GetMapping("/geren/list")
    @RequiresPermissions("chaxun:geren")
    //@SystemLog("薪酬查询个人")
    public Result<List<Xinchoushuju>> querygerenList(@RequestParam(name = "fangan") String fangan) {
        if(fangan == null ){
            return Result.failure(ResultCodeEnum.BAD_REQUEST);
        }
        Long fanganid = Long.valueOf(fangan);
        Subject subject = SecurityUtils.getSubject();
        String username = (String)subject.getPrincipal();
        User user = userService.queryOne(new User().setUsername(username));
        
        return Result.success(xinchoushujuService.gerenchaxun(fanganid, user));
    }
        
    
}
