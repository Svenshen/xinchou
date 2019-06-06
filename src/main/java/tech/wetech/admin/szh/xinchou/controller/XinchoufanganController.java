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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.wetech.admin.core.annotation.SystemLog;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.szh.xinchou.domain.Xinchoufangan;
import tech.wetech.admin.szh.xinchou.service.XinchoufanganService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-23 8:53:38
 */
@Controller
@RequestMapping("/xinchou/jisuan/fangan")
public class XinchoufanganController {
    
    @Autowired
    XinchoufanganService xinchoufanganService;
    
    @GetMapping
    @RequiresPermissions("jisuan:fangan")
    public String page(){
        return "xinchou/jisuanfangan";
    }
    
    @ResponseBody
    @PostMapping("/create")
    @RequiresPermissions("jisuanfangan:create")
    @SystemLog("薪酬方案创建")
    public Result create(Xinchoufangan xinchoufangan) throws Exception  {
        xinchoufanganService.add(xinchoufangan);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("jisuanfangan:delete")
    @SystemLog("薪酬方案删除")
    public Result delete(Xinchoufangan xinchoufangan)   {
        xinchoufanganService.delete(xinchoufangan);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("jisuanfangan:update")
    @SystemLog("薪酬方案修改")
    public Result update(Xinchoufangan xinchoufangan)  {
        xinchoufanganService.update(xinchoufangan);
        return Result.success();
    }
    
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("jisuanfangan:view")
    public Result<List<Xinchoufangan>> queryList() {
        List<Xinchoufangan> xinchoufangans =  xinchoufanganService.queryAll();
        return Result.success(xinchoufangans);
    }

}
