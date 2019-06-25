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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.wetech.admin.core.annotation.SystemLog;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.modules.system.po.Organization;
import tech.wetech.admin.modules.system.po.User;
import tech.wetech.admin.modules.system.service.OrganizationService;
import tech.wetech.admin.modules.system.service.UserService;
import tech.wetech.admin.modules.system.vo.UserVO;
import tech.wetech.admin.szh.xinchou.domain.Shoujidanjiaticheng;
import tech.wetech.admin.szh.xinchou.domain.ShoujidanjiatichengId;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehu;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehuleibie;
import tech.wetech.admin.szh.xinchou.domain.Shoujileibie;
import tech.wetech.admin.szh.xinchou.domain.ShoujiyewuLeibie;
import tech.wetech.admin.szh.xinchou.service.ShoujidanjiatichengService;
import tech.wetech.admin.szh.xinchou.service.ShoujikehuService;
import tech.wetech.admin.szh.xinchou.service.ShoujikehuleibieService;
import tech.wetech.admin.szh.xinchou.service.ShoujileibieService;
import tech.wetech.admin.szh.xinchou.service.ShoujiyewuLeibieService;
import tech.wetech.admin.szh.xinchou.vo.ShoujidanjiatichengVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-21 9:51:25
 */
@Controller
@RequestMapping("/xinchou/shouji/danjia")
public class ShoujidanjiatichengController {
    
    @Autowired
    OrganizationService organizationService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ShoujileibieService shoujileibieService;
    
    @Autowired
    ShoujikehuleibieService shoujikehuleibieService;
    
    @Autowired
    ShoujidanjiatichengService shoujidanjiatichengService;
    
            
    @GetMapping
    @RequiresPermissions("shouji:danjia")
    public String page(){
        return "xinchou/shoujidanjia";
    }
    
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("shoujidanjia:view")
    public Result<List<ShoujidanjiatichengVO>> queryList() {
        List<Organization> organizations = organizationService.queryList(new Organization().setLeaf(true));
        
        List<Shoujileibie> shoujiyewuleibies = shoujileibieService.queryAll();
        List<Shoujikehuleibie> shoujikehuleibies = shoujikehuleibieService.queryAll();
        List<UserVO> uservos = new ArrayList() ;
        organizations.forEach((organization) -> {
            List<User> users = userService.queryList(new User().setOrganizationId(organization.getId()));
            users.forEach((user)->{
                UserVO userVO= new UserVO(user);
                userVO.setOrganizationName(getOrganizationName(user.getOrganizationId()));
                uservos.add(userVO);
            });
        });         
        for(UserVO uv:uservos){
            for(Shoujileibie shoujiyewuleibie:shoujiyewuleibies){
                for(Shoujikehuleibie shoujikehuleibie:shoujikehuleibies){
                    ShoujidanjiatichengId shoujidanjiatichengId = new ShoujidanjiatichengId();
                    shoujidanjiatichengId.setBumenid(uv.getOrganizationId());
                    shoujidanjiatichengId.setKehuid(shoujikehuleibie.getId());
                    shoujidanjiatichengId.setName(uv.getName());
                    shoujidanjiatichengId.setYewuid(shoujiyewuleibie.getId());
                    if (shoujidanjiatichengService.queryById(shoujidanjiatichengId) == null){
                        Shoujidanjiaticheng shoujidanjiaticheng = new  Shoujidanjiaticheng();
                        shoujidanjiaticheng.setBumenid(uv.getOrganizationId());
                        shoujidanjiaticheng.setKehuid(shoujikehuleibie.getId());
                        shoujidanjiaticheng.setName(uv.getName());
                        shoujidanjiaticheng.setYewuid(shoujiyewuleibie.getId());
                        shoujidanjiatichengService.saveAndupdate(shoujidanjiaticheng);
                    } 
                }
            }
        }
        List<ShoujidanjiatichengVO> shoujidanjiatichengVOs = new ArrayList() ;
        List<Shoujidanjiaticheng> shoujidanjiatichengs =  shoujidanjiatichengService.queryAll();
        for(Shoujidanjiaticheng shoujidanjiaticheng:shoujidanjiatichengs){
            ShoujidanjiatichengVO shoujidanjiatichengVO = new ShoujidanjiatichengVO(shoujidanjiaticheng, getOrganizationName(shoujidanjiaticheng.getBumenid()), getYewuName(shoujidanjiaticheng.getYewuid()), getKehuName(shoujidanjiaticheng.getKehuid()));
            shoujidanjiatichengVOs.add(shoujidanjiatichengVO);
        }
        return Result.success(shoujidanjiatichengVOs);
    }
    
    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("shoujidanjia:delete")
    @SystemLog("收寄单价删除")
    public Result delete(ShoujidanjiatichengVO shoujidanjiatichengVO)   {
        shoujidanjiatichengService.delete(new Shoujidanjiaticheng(shoujidanjiatichengVO));
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("shoujidanjia:update")
    @SystemLog("收寄单价修改")
    public Result update(ShoujidanjiatichengVO shoujidanjiatichengVO)  {
        
        shoujidanjiatichengService.update(new Shoujidanjiaticheng(shoujidanjiatichengVO));
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
        Shoujileibie shoujileibie = shoujileibieService.queryById(yewuid);
        if (shoujileibie == null) {
            return "";
        }
        return shoujileibie.getName();
    }
    
    private String getKehuName(Long kehuleibieid) {
        if(kehuleibieid == null){
            return "";
        }
        Shoujikehuleibie shoujikehuleibie = shoujikehuleibieService.queryById(kehuleibieid);
        if (shoujikehuleibie == null) {
            return "";
        }
        return shoujikehuleibie.getName();
    }
    

}
