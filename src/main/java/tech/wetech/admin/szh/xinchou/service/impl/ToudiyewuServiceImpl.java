/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service.impl;

import java.util.List;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import tech.wetech.admin.szh.xinchou.dao.ToudiyewuDao;
import tech.wetech.admin.szh.xinchou.domain.Toudiyewu;
import tech.wetech.admin.szh.xinchou.service.ToudiyewuService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-9 14:19:12
 */
@Service
public class ToudiyewuServiceImpl extends XinchouBaseService<Toudiyewu,Long> implements ToudiyewuService{

    
    @Autowired
    ToudiyewuDao toudiyewuDao;
    
 

//    @Override
//    public List findAll(Toudiyewu toudiyewu) {
//        Example<Toudiyewu> toudiyewuExample = Example.of(toudiyewu);
//        return toudiyewuDao.findAll(toudiyewuExample);
//    }
//
//    @Override
//    public Toudiyewu findOne(Toudiyewu toudiyewu) {
//        Example<Toudiyewu> toudiyewuExample = Example.of(toudiyewu);
//        //投递业务记录不唯一
//        return toudiyewuDao.findOne(toudiyewuExample).get();
//    }

    @Override
    public void add(Toudiyewu toudiyewu) throws Exception{
        if(queryOne(toudiyewu) != null){
            throw new Exception("投递业务已存在");
        }
        toudiyewuDao.save(toudiyewu);
    }

    @Override
    public void update(Toudiyewu toudiyewu) {
        toudiyewuDao.save(toudiyewu);
    }

}
