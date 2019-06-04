/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.wetech.admin.core.annotation.SystemLog;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehuleibie;
import tech.wetech.admin.szh.xinchou.domain.Shoujileibie;
import tech.wetech.admin.szh.xinchou.service.ShoujikehuleibieService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-16 17:05:43
 */
@Controller
@RequestMapping("/xinchou/shouji/kehuleibie")
public class ShoujikehuleibieController {
    
    @Autowired
    ShoujikehuleibieService shoujikehuleibieService;
    
    @GetMapping
    @RequiresPermissions("shouji:kehuleibie")
    public String page(){
        return "/xinchou/shoujikehuleibie";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("shoujikehuleibie:view")
    public Result<List<Shoujikehuleibie>> queryList() {
        List<Shoujikehuleibie> shoujikehuleibies =  shoujikehuleibieService.queryAll();
        return Result.success(shoujikehuleibies);
    }
    
    @ResponseBody
    @PostMapping("/create")
    @RequiresPermissions("shoujileibie:create")
    @SystemLog("收寄客户类别创建")
    public Result create(Shoujikehuleibie shoujikehuleibie) throws Exception  {
        shoujikehuleibieService.add(shoujikehuleibie);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("shoujileibie:delete")
    @SystemLog("收寄客户类别删除")
    public Result delete(Shoujikehuleibie shoujikehuleibie)   throws Exception{
        shoujikehuleibieService.deletekehu(shoujikehuleibie);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("shoujileibie:update")
    @SystemLog("收寄客户类别修改")
    public Result update(Shoujikehuleibie shoujikehuleibie)  {
        shoujikehuleibieService.update(shoujikehuleibie);
        return Result.success();
    }
    
}
