/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.wetech.admin.szh.xinchou.dao.ShoujikehuleibieDao;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehuleibie;
import tech.wetech.admin.szh.xinchou.service.ShoujikehuleibieService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-16 16:58:55
 */
@Service
public class ShoujikehuleibieServiceImpl extends XinchouBaseService<Shoujikehuleibie,Long> implements ShoujikehuleibieService{

    

    @Autowired
    ShoujikehuleibieDao shoujikehuleibieDao;
    
    @Override
    public void add(Shoujikehuleibie shoujikehuleibie) throws Exception {
        
        if(queryOne(shoujikehuleibie) != null){
            throw new Exception("收寄客户类别已存在");
        }
        shoujikehuleibieDao.save(shoujikehuleibie);
    }

    @Override
    public void update(Shoujikehuleibie shoujikehuleibie) {
        shoujikehuleibieDao.save(shoujikehuleibie);
    }
    
    @Override
    public void deletekehu(Shoujikehuleibie shoujikehuleibie) throws Exception{
        if(shoujikehuleibie.getName().equals("散户默认") || shoujikehuleibie.getName().equals("大客户默认")){
            throw new Exception("默认类别不能删除");
        }
        shoujikehuleibieDao.delete(shoujikehuleibie);
    }        
    
    

}
