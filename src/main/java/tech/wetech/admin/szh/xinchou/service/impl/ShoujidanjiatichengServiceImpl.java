/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    
    @Override
    public void update(List<Shoujidanjiaticheng> shoujidanjiatichengs) {
        shoujidanjiatichengDao.saveAll(shoujidanjiatichengs);
    }

    @Override
    public void update(Shoujidanjiaticheng shoujidanjiaticheng) {
        shoujidanjiatichengDao.save(shoujidanjiaticheng);
    }
    
    

}
