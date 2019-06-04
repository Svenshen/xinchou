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
import tech.wetech.admin.szh.xinchou.domain.Shoujileibie;
import tech.wetech.admin.szh.xinchou.service.ShoujileibieService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-15 17:24:02
 */
@Controller
@RequestMapping("/xinchou/shouji/leibie")
public class ShoujileibieController {
    
    @Autowired
    ShoujileibieService shoujileibieService;
    
    @GetMapping
    @RequiresPermissions("shouji:leibie")
    public String page(){
        return "/xinchou/shoujileibie";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("shoujileibie:view")
    public Result<List<Shoujileibie>> queryList() {
        List<Shoujileibie> shoujileibies =  shoujileibieService.queryAll();
        return Result.success(shoujileibies);
    }
    
    @ResponseBody
    @PostMapping("/create")
    @RequiresPermissions("shoujileibie:create")
    @SystemLog("收寄类别创建")
    public Result create(Shoujileibie shoujileibie) throws Exception  {
        shoujileibieService.add(shoujileibie);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("shoujileibie:delete")
    @SystemLog("收寄类别删除")
    public Result delete(Shoujileibie shoujileibie)   {
        shoujileibieService.delete(shoujileibie);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("shoujileibie:update")
    @SystemLog("收寄类别修改")
    public Result update(Shoujileibie shoujileibie)  {
        shoujileibieService.update(shoujileibie);
        return Result.success();
    }
}
