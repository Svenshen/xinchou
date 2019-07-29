/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import tech.wetech.admin.modules.system.dto.TreeDto;
import tech.wetech.admin.modules.system.po.User;
import tech.wetech.admin.modules.system.service.OrganizationService;
import tech.wetech.admin.szh.xinchou.dao.ShoujidanjiatichengDao;
import tech.wetech.admin.szh.xinchou.domain.Shoujidanjiaticheng;
import tech.wetech.admin.szh.xinchou.domain.ShoujidanjiatichengId;
import tech.wetech.admin.szh.xinchou.service.ShoujidanjiatichengService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-21 10:02:13
 */
@Service
public class ShoujidanjiatichengServiceImpl extends XinchouBaseService<Shoujidanjiaticheng,ShoujidanjiatichengId> implements ShoujidanjiatichengService{

    @Autowired
    ShoujidanjiatichengDao shoujidanjiatichengDao;
    @Autowired
    OrganizationService organizationService;
    
    @Override
    public void update(List<Shoujidanjiaticheng> shoujidanjiatichengs) {
        shoujidanjiatichengDao.saveAll(shoujidanjiatichengs);
    }

    @Override
    public void update(Shoujidanjiaticheng shoujidanjiaticheng) {
        shoujidanjiatichengDao.save(shoujidanjiaticheng);
    }

    @Override
    public List<Shoujidanjiaticheng> querybybumenlist(User user) {
        
        return shoujidanjiatichengDao.findbybumenlist(getzibumen(user.getOrganizationId()));
        
        
    }
    
    private List<Long> getzibumen(Long bumenid){
        List listids = new ArrayList();
        List<TreeDto> list = organizationService.queryOrgTree(bumenid);
        for(TreeDto t : list){
            if(t.isParent()){
                listids.addAll(getzibumen(t.getId()));
            }else{
                listids.add(t.getId());
            }
        }
        return listids;
    }

}
