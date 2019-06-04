/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.controller;

import com.github.pagehelper.Page;
import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.wetech.admin.core.annotation.SystemLog;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.szh.xinchou.domain.Toudiyewu;
import tech.wetech.admin.szh.xinchou.service.ToudiyewuService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-9 15:09:01
 */
@Controller
@RequestMapping("/xinchou/toudi/leibie")
public class ToudileibieController {
    
    @Autowired
    ToudiyewuService toudiyewuService;
    
    @GetMapping
    @RequiresPermissions("toudi:leibie")
    public String page(){
        return "/xinchou/toudileibie";
    }
    
    @ResponseBody
    @PostMapping("/create")
    @RequiresPermissions("toudileibie:create")
    @SystemLog("投递类别创建")
    public Result create(Toudiyewu toudiyewu) throws Exception  {
        toudiyewuService.add(toudiyewu);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("toudileibie:delete")
    @SystemLog("投递类别删除")
    public Result delete(Toudiyewu toudiyewu)   {
        toudiyewuService.delete(toudiyewu);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("toudileibie:update")
    @SystemLog("投递类别修改")
    public Result update(Toudiyewu toudiyewu)  {
        toudiyewuService.update(toudiyewu);
        return Result.success();
    }
    
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("toudileibie:view")
    public Result<List<Toudiyewu>> queryList() {
        List<Toudiyewu> toudiyewus =  toudiyewuService.queryAll();
        return Result.success(toudiyewus);
    }
    
    
}
