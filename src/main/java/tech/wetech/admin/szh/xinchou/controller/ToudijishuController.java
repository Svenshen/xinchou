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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.wetech.admin.core.annotation.SystemLog;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.modules.system.po.Organization;
import tech.wetech.admin.szh.xinchou.domain.Toudijishu;
import tech.wetech.admin.szh.xinchou.domain.Toudiyewu;
import tech.wetech.admin.szh.xinchou.service.ToudijishuService;
import tech.wetech.admin.szh.xinchou.service.ToudiyewuService;
import tech.wetech.admin.szh.xinchou.vo.ToudijishuVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-17 8:15:42
 */
@Controller
@RequestMapping("/xinchou/toudi/jishu")
public class ToudijishuController {

    @Autowired
    ToudijishuService toudijishuService;
    @Autowired
    ToudiyewuService toudiyewuService;
    
    @ModelAttribute("yewuList")
    public List<Toudiyewu> yewulist(){
        return toudiyewuService.queryAll();
    }
    
    
    @GetMapping
    @RequiresPermissions("toudi:jishu")
    public String page(Model model){
        return "xinchou/toudijishu";
    }
    
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("toudijishu:view")
    public Result<List<ToudijishuVO>> queryList() {
        List<ToudijishuVO> toudijishuVOs = new ArrayList();
        for(Toudijishu t :toudijishuService.queryAll()){
            ToudijishuVO tvo = new ToudijishuVO(t,getYewuName(t.getId()));
            toudijishuVOs.add(tvo);
        }
        return Result.success(toudijishuVOs);
    }
    
    @ResponseBody
    @PostMapping("/create")
    @RequiresPermissions("toudijishu:create")
    @SystemLog("投递基数创建")
    public Result create(Toudijishu toudijishu) throws Exception  {
        toudijishuService.add(toudijishu);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("toudijishu:delete")
    @SystemLog("投递基数删除")
    public Result delete(Toudijishu toudijishu)   {
        System.out.println(toudijishu);
        toudijishuService.delete(toudijishu);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("toudijishu:update")
    @SystemLog("投递基数修改")
    public Result update(Toudijishu toudijishu)  {
        toudijishuService.update(toudijishu);
        return Result.success();
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
