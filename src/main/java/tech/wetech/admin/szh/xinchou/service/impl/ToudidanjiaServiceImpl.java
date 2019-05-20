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


    @Override
    public void update(Toudidanjia toudidanjia) {
        toudidanjiaDao.save(toudidanjia);
    }

    @Override
    public void update(List<Toudidanjia> toudidanjias) {
        toudidanjiaDao.saveAll(toudidanjias);
    }
     
     

}
