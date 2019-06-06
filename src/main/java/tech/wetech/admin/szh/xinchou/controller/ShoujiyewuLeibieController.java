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
import tech.wetech.admin.szh.xinchou.domain.Shoujileibie;
import tech.wetech.admin.szh.xinchou.domain.ShoujiyewuLeibie;
import tech.wetech.admin.szh.xinchou.service.ShoujileibieService;
import tech.wetech.admin.szh.xinchou.service.ShoujishujuService;
import tech.wetech.admin.szh.xinchou.service.ShoujiyewuLeibieService;
import tech.wetech.admin.szh.xinchou.vo.ShoujiyewuLeibieVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-16 8:44:43
 */
@Controller
@RequestMapping("/xinchou/shouji/yewuleibie")
public class ShoujiyewuLeibieController {
    
    @Autowired
    ShoujiyewuLeibieService shoujiyewuLeibieService;
    @Autowired
    ShoujishujuService shoujishujuService;
    @Autowired
    ShoujileibieService shoujileibieService;
    
    
    @ModelAttribute("leibieList")
    public List leibielist(){
        return shoujileibieService.queryAll();
    }
    
    @GetMapping
    @RequiresPermissions("shouji:yewuleibie")
    public String page(Model model){
        return "xinchou/shoujiyewuleibie";
    }
    
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("shoujiyewuleibie:view")
    public Result<List<ShoujiyewuLeibieVO>> queryList() {
        List<String> shoujiyewus = shoujishujuService.queryyewulist();
        shoujiyewus.forEach((shoujiyewu)->{
            if(shoujiyewuLeibieService.queryById(shoujiyewu) == null){
                ShoujiyewuLeibie shoujiyewuLeibie = new ShoujiyewuLeibie();
                shoujiyewuLeibie.setShoujiyewu(shoujiyewu);
                shoujiyewuLeibieService.saveAndupdate(shoujiyewuLeibie);
            }
        });
        List<ShoujiyewuLeibie> shoujiyewuLeibies = shoujiyewuLeibieService.queryAll();
        List<ShoujiyewuLeibieVO> shoujiyewuLeibieVOs = new ArrayList();
        shoujiyewuLeibies.forEach((shoujiyewuleibie)->{
            ShoujiyewuLeibieVO shoujiyewuLeibieVO = new ShoujiyewuLeibieVO(shoujiyewuleibie, getYewuleibieName(shoujiyewuleibie.getShoujileibieid()));
            shoujiyewuLeibieVOs.add(shoujiyewuLeibieVO);
        });
        return Result.success(shoujiyewuLeibieVOs);
    }
    
    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("shoujiyewuleibie:delete")
    @SystemLog("收寄业务类别删除")
    public Result delete(ShoujiyewuLeibieVO shoujiyewuLeibieVO)   {
        shoujiyewuLeibieService.delete(new ShoujiyewuLeibie(shoujiyewuLeibieVO));
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("shoujiyewuleibie:update")
    @SystemLog("收寄业务类别修改")
    public Result update(ShoujiyewuLeibieVO shoujiyewuLeibieVO)  {
        shoujiyewuLeibieService.saveAndupdate(new ShoujiyewuLeibie(shoujiyewuLeibieVO));
        return Result.success();
    }
    
    private String getYewuleibieName(Long yewuleibieid) {
        if(yewuleibieid == null){
            return "";
        }
        Shoujileibie toudiyewu = shoujileibieService.queryById(yewuleibieid);
        if (toudiyewu == null) {
            return "";
        }
        return toudiyewu.getName();
    }

}
