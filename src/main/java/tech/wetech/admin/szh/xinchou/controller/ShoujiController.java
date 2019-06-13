/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tech.wetech.admin.core.annotation.SystemLog;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.core.utils.ResultCodeEnum;
import tech.wetech.admin.modules.system.po.Organization;
import tech.wetech.admin.modules.system.service.OrganizationService;
import tech.wetech.admin.szh.xinchou.domain.Shoujishuju;
import tech.wetech.admin.szh.xinchou.service.ShoujishujuService;
import tech.wetech.admin.szh.xinchou.vo.Shoujikehu_LeibieVO;
import tech.wetech.admin.szh.xinchou.vo.ShoujishujuVO;
import tech.wetech.admin.szh.xinchou.vo.StringVO;

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
    
    @ModelAttribute("chaxunids")
    public List chaxunidlist(){
        List<String> daoruids = shoujishujuService.querydaoruidlist();
        List<StringVO> stringvos = new ArrayList();
        for(String daoruid:daoruids){
            StringVO stringvo = new StringVO(daoruid);
            stringvos.add(stringvo);
        }
        return stringvos;
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
        Subject subject = SecurityUtils.getSubject();
        String username = (String)subject.getPrincipal();
        String daoruid = username+System.currentTimeMillis();
        shoujishujuService.exceldaoru(file, bumen,daoruid);
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('导入成功');</script>");
        out.print("<script>location='/#shouji';</script>");
        out.flush();
        //return "";
    } 
    
    @GetMapping("/daoruchaxun")
    @RequiresPermissions("shoujidaoru:chaxun")
    public String daoruidchaxunpage(){
        return "xinchou/shoujidaoruchaxun";
    }
    
    @ResponseBody
    @GetMapping("/daoruchaxun/list")
    @RequiresPermissions("shoujidaoru:chaxun")
    public Result<List<ShoujishujuVO>> queryList(@RequestParam("chaxunid") String chaxunid,@RequestParam("pageSize") int pasgesize,@RequestParam("pageNumber") int pagenumber) {
        if(chaxunid == null){
            return Result.failure(ResultCodeEnum.BAD_REQUEST);
        }
        
        Shoujishuju chaxunshouji = new Shoujishuju();
        chaxunshouji.setDaoruid(chaxunid);
        Pageable pageable = PageRequest.of(pagenumber, pasgesize);
        Page<Shoujishuju> page = shoujishujuService.queryList(chaxunshouji, pageable);
        List<Shoujishuju> shoujishus = page.getContent();
        List<ShoujishujuVO> shoujishujuVOs = new ArrayList();
        for(Shoujishuju shoujishuju : shoujishus){
            ShoujishujuVO shoujishujuVO = new ShoujishujuVO(shoujishuju,getOrganizationName(Long.valueOf(shoujishuju.getShoujijigou())));
            shoujishujuVOs.add(shoujishujuVO);
        }
        return Result.success(shoujishujuVOs).addExtra("total", page.getTotalElements());
    }
   
    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("shoujidaoru:chaxun")
    @SystemLog("收寄导入删除")
    public Result delete(@RequestParam("chaxunid") String chaxunid){
        shoujishujuService.deletedaoruid(chaxunid);
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
    
}
