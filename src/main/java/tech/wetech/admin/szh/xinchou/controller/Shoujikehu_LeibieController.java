/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.wetech.admin.core.annotation.SystemLog;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehu;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehu_Leibie;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehuleibie;
import tech.wetech.admin.szh.xinchou.service.ShoujikehuService;
import tech.wetech.admin.szh.xinchou.service.Shoujikehu_LeibieService;
import tech.wetech.admin.szh.xinchou.service.ShoujikehuleibieService;
import tech.wetech.admin.szh.xinchou.service.ShoujishujuService;
import tech.wetech.admin.szh.xinchou.vo.Shoujikehu_LeibieVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-17 10:51:46
 */
@Controller
@RequestMapping("/xinchou/shouji/kehu")
public class Shoujikehu_LeibieController {
    
    @Autowired
    Shoujikehu_LeibieService shoujikehu_LeibieService;
    @Autowired
    ShoujikehuleibieService shoujikehuleibieService;
    @Autowired
    ShoujikehuService shoujikehuService;
    @Autowired
    ShoujishujuService shoujishujuService;
    
    
    @ModelAttribute("leibieList")
    public List leibielist(){
        return shoujikehuleibieService.queryAll();
    }
    
    @GetMapping
    @RequiresPermissions("shouji:kehu")
    public String page(){
        return "xinchou/shoujikehu";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("shoujikehu:view")
    public Result<List<Shoujikehu_LeibieVO>> queryList() {
        List<Shoujikehu_LeibieVO> shoujikehu_LeibieVOs = new ArrayList();
        List<Shoujikehu> shoujikehus = shoujishujuService.querykehulist();
        shoujikehuService.saveAndupdateAll(shoujikehus);
        shoujikehus.forEach((shoujikehu)->{
            if(shoujikehu_LeibieService.queryById(shoujikehu.getKehudaima()) == null){
                Shoujikehu_Leibie s = new Shoujikehu_Leibie();
                s.setKehudaima(shoujikehu.getKehudaima());
                shoujikehu_LeibieService.saveAndupdate(s);
            }
        });
        List<Shoujikehu_Leibie> shoujikehu_leibies = shoujikehu_LeibieService.queryAll();
        shoujikehu_leibies.forEach((shoujikehu_leibie)->{
            Shoujikehu_LeibieVO shoujikehu_LeibieVO = new Shoujikehu_LeibieVO(shoujikehu_leibie, getKehumingcheng(shoujikehu_leibie.getKehudaima()), getLeibiemingcheng(shoujikehu_leibie.getLeibieid()));
            shoujikehu_LeibieVOs.add(shoujikehu_LeibieVO);
        });
        return Result.success(shoujikehu_LeibieVOs);
    }
    
    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("shoujikehu:delete")
    @SystemLog("收寄客户类别关系删除")
    public Result delete(Shoujikehu_LeibieVO shoujikehu_LeibieVO)   {
        shoujikehu_LeibieService.delete(new Shoujikehu_Leibie(shoujikehu_LeibieVO));
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("shoujikehu:update")
    @SystemLog("收寄客户类别关系修改")
    public Result update(Shoujikehu_LeibieVO shoujikehu_LeibieVO)  {
        shoujikehu_LeibieService.saveAndupdate(new Shoujikehu_Leibie(shoujikehu_LeibieVO));
        return Result.success();
    }
    
    private String getKehumingcheng(String kehudaima) {
        if(kehudaima == null){
            return "";
        }
        Shoujikehu shoujikehu = shoujikehuService.queryById(kehudaima);
        if (shoujikehu == null) {
            return "";
        }
        return shoujikehu.getKehumingcheng();
    }
    
    
    private String getLeibiemingcheng(Long id) {
        if(id == null){
            return "";
        }
        Shoujikehuleibie shoujikehuleibie = shoujikehuleibieService.queryById(id);
        if (shoujikehuleibie == null) {
            return "";
        }
        return shoujikehuleibie.getName();
    }

    
}
