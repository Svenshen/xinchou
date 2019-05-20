/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tech.wetech.admin.core.annotation.SystemLog;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.modules.system.po.Organization;
import tech.wetech.admin.modules.system.service.OrganizationService;
import tech.wetech.admin.szh.xinchou.service.ShoujishujuService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-7 9:15:21
 */
@Controller
@RequestMapping("/shouji")
public class ShoujiController {
    
    
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    ShoujishujuService shoujishujuService;
    
    @ModelAttribute("bumenList")
    public List bumenlist(){
        return organizationService.queryList(new Organization().setLeaf(true));
    }
    
    @GetMapping
    @RequiresPermissions("shouji:daoru")
    public String shoujidaorupage(){
        return "xinchou/shoujidaoru";
    }
    
    @PostMapping("/daoru")
    @RequiresPermissions("shouji:daoru")
    @SystemLog("收寄信息导入")
    public void daoru(@RequestParam("shoujifile") MultipartFile file,@RequestParam("bumen") String bumen,Model model,HttpServletResponse response) throws IOException,Exception{
        shoujishujuService.exceldaoru(file, bumen);
        
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('导入成功');</script>");
        out.print("<script>location='/#shouji';</script>");
        out.flush();
        //return "";
    } 

}
