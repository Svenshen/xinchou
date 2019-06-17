/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import tech.wetech.admin.szh.xinchou.domain.Toudishuju;
import tech.wetech.admin.szh.xinchou.domain.Toudiyewu;
import tech.wetech.admin.szh.xinchou.service.ShoujishujuService;
import tech.wetech.admin.szh.xinchou.service.ToudishujuService;
import tech.wetech.admin.szh.xinchou.service.ToudiyewuService;
import tech.wetech.admin.szh.xinchou.vo.StringVO;
import tech.wetech.admin.szh.xinchou.vo.ToudishujuVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-7 9:15:21
 */
@Controller
@RequestMapping("/toudi")
public class ToudiController {
    
    
    @Autowired
    OrganizationService organizationService;
    @Autowired
    ToudishujuService toudishujuService;
    @Autowired
    ToudiyewuService toudiyewuService;
    
    
    @ModelAttribute("bumenList")
    public List bumenlist(){
        return organizationService.queryList(new Organization().setLeaf(true));
    }
    
    @ModelAttribute("yewuList")
    public List yewulist(){
        return toudiyewuService.queryAll();
    }
    
    @ModelAttribute("chaxunids")
    public List chaxunidlist(){
        List<String> daoruids = toudishujuService.querydaoruidlist();
        List<StringVO> stringvos = new ArrayList();
        for(String daoruid:daoruids){
            StringVO stringvo = new StringVO(daoruid);
            stringvos.add(stringvo);
        }
        return stringvos;
    }
    
    @GetMapping
    @RequiresPermissions("toudi:daoru")
    public String shoujidaorupage(){
        return "xinchou/toudidaoru";
    }
    
    @PostMapping("/daoru")
    @RequiresPermissions("toudi:daoru")
    @SystemLog("投递信息导入")
    public void daoru(@RequestParam("shoujifile") MultipartFile file,@RequestParam("bumen") Long bumen,@RequestParam("yewu") Long yewu,@RequestParam("shijian") String shijian,HttpServletResponse response) throws IOException,Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Subject subject = SecurityUtils.getSubject();
        String username = (String)subject.getPrincipal();
        String daoruid = username+System.currentTimeMillis();
        toudishujuService.exceldaoru(file, bumen,yewu,sdf.parse(shijian),daoruid);
        toudishujuService.deleteheji();
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('导入成功');</script>");
        out.print("<script>location='/#toudi';</script>");
        out.flush();
        //return "";
    } 
    
    @GetMapping("/daoruchaxun")
    @RequiresPermissions("toudidaoru:chaxun")
    public String daoruidchaxunpage(){
        return "xinchou/toudidaoruchaxun";
    }
    
        
    @ResponseBody
    @GetMapping("/daoruchaxun/list")
    @RequiresPermissions("toudidaoru:chaxun")
    public Result<List<ToudishujuVO>> queryList(@RequestParam("chaxunid") String chaxunid,@RequestParam("pageSize") int pasgesize,@RequestParam("pageNumber") int pagenumber) {
        if(chaxunid == null){
            return Result.failure(ResultCodeEnum.BAD_REQUEST);
        }
        
        Toudishuju chaxunshouji = new Toudishuju();
        chaxunshouji.setDaoruid(chaxunid);
        Pageable pageable = PageRequest.of(pagenumber-1, pasgesize);
        Page<Toudishuju> page = toudishujuService.queryList(chaxunshouji, pageable);
        List<Toudishuju> shoujishus = page.getContent();
        List<ToudishujuVO> shoujishujuVOs = new ArrayList();
        for(Toudishuju shoujishuju : shoujishus){
            ToudishujuVO shoujishujuVO = new ToudishujuVO(shoujishuju,getYewuName(shoujishuju.getYewuid()),getOrganizationName(shoujishuju.getBumenid()));
            shoujishujuVOs.add(shoujishujuVO);
        }
        return Result.success(shoujishujuVOs).addExtra("total", page.getTotalElements());
    }
    
    
    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("toudidaoru:chaxun")
    @SystemLog("投递导入删除")
    public Result delete(@RequestParam("chaxunid") String chaxunid){
        toudishujuService.deletedaoruid(chaxunid);
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
