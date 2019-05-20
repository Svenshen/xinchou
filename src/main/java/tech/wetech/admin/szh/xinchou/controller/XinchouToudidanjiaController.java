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
import tech.wetech.admin.szh.xinchou.domain.Toudidanjia;
import tech.wetech.admin.szh.xinchou.domain.ToudidanjiaId;
import tech.wetech.admin.szh.xinchou.domain.Toudiyewu;
import tech.wetech.admin.szh.xinchou.service.ToudidanjiaService;
import tech.wetech.admin.szh.xinchou.service.ToudiyewuService;
import tech.wetech.admin.szh.xinchou.vo.ToudidanjiaVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-13 15:06:13
 */
@Controller
@RequestMapping("/xinchou/toudi/danjia")
public class XinchouToudidanjiaController {
    
    @Autowired
    ToudidanjiaService toudidanjiaService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    UserService userService;
    @Autowired
    ToudiyewuService toudiyewuService;
    
    
    @GetMapping
    @RequiresPermissions("toudi:danjia")
    public String page(Model model){
        return "/xinchou/toudidanjia";
    }
    
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("toudidanjia:view")
    public Result<List<ToudidanjiaVO>> queryList() {
        List<Organization> organizations = organizationService.queryList(new Organization().setLeaf(true));
        List<UserVO> uservos = new ArrayList() ;
        organizations.forEach((organization) -> {
            List<User> users = userService.queryList(new User().setOrganizationId(organization.getId()));
            users.forEach((user)->{
                UserVO userVO= new UserVO(user);
                userVO.setOrganizationName(getOrganizationName(user.getOrganizationId()));
                uservos.add(userVO);
            });
        }); 
        List<Toudiyewu> toudiyewus = toudiyewuService.queryAll();
        for(UserVO uv:uservos){
            for(Toudiyewu t:toudiyewus){
                ToudidanjiaId td = new ToudidanjiaId();
                td.setBumenid(uv.getOrganizationId());
                td.setName(uv.getName());
                td.setYewuid(t.getId());
                if(toudidanjiaService.queryById(td) == null){
                    Toudidanjia tt = new Toudidanjia();
                    tt.setBumenid(uv.getOrganizationId());
                    tt.setName(uv.getName());
                    tt.setYewuid(t.getId());
                    toudidanjiaService.saveAndupdate(tt);
                }
            }
        }
        List<ToudidanjiaVO>  toudidanjiaVOs = new ArrayList();
        List<Toudidanjia> toudidanjias =  toudidanjiaService.queryAll();
        for(Toudidanjia t:toudidanjias){
            ToudidanjiaVO tv = new ToudidanjiaVO(t);
            tv.setYewu(getYewuName(t.getYewuid()));
            tv.setBumen(getOrganizationName(t.getBumenid()));
            toudidanjiaVOs.add(tv);
        }
        
        return Result.success(toudidanjiaVOs);
    }

    
    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("toudileibie:delete")
    @SystemLog("投递单价删除")
    public Result delete(ToudidanjiaVO toudidanjiaVO)   {
        toudidanjiaService.delete(new Toudidanjia(toudidanjiaVO));
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("toudileibie:update")
    @SystemLog("投递单价修改")
    public Result update(ToudidanjiaVO toudidanjiaVO)  {
        toudidanjiaService.update(new Toudidanjia(toudidanjiaVO));
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
