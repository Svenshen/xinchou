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
import org.springframework.stereotype.Service;
import tech.wetech.admin.modules.system.dto.TreeDto;
import tech.wetech.admin.modules.system.po.User;
import tech.wetech.admin.modules.system.service.OrganizationService;
import tech.wetech.admin.szh.xinchou.dao.ToudidanjiaDao;
import tech.wetech.admin.szh.xinchou.domain.Toudidanjia;
import tech.wetech.admin.szh.xinchou.domain.ToudidanjiaId;
import tech.wetech.admin.szh.xinchou.service.ToudidanjiaService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-13 14:51:21
 */
@Service
public class ToudidanjiaServiceImpl extends XinchouBaseService<Toudidanjia,ToudidanjiaId> implements ToudidanjiaService{
    
     @Autowired
     ToudidanjiaDao toudidanjiaDao;

     @Autowired
     OrganizationService organizationService;

    @Override
    public void update(Toudidanjia toudidanjia) {
        toudidanjiaDao.save(toudidanjia);
    }

    @Override
    public void update(List<Toudidanjia> toudidanjias) {
        toudidanjiaDao.saveAll(toudidanjias);
    }

    @Override
    public List<Toudidanjia> findbybumenList(User user) {
        return toudidanjiaDao.findbybumenlist(getzibumen(user.getOrganizationId()));
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
