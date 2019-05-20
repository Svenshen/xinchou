/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.wetech.admin.szh.xinchou.dao.ShoujileibieDao;
import tech.wetech.admin.szh.xinchou.domain.Shoujileibie;
import tech.wetech.admin.szh.xinchou.service.ShoujileibieService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-15 17:18:54
 */
@Service
public class ShoujileibieServiceImpl extends XinchouBaseService<Shoujileibie,Long> implements ShoujileibieService{

    @Autowired
    ShoujileibieDao shoujileibieDao;
    
    @Override
    public void add(Shoujileibie shoujileibie) throws Exception {
        if(queryOne(shoujileibie) != null){
            throw new Exception("收寄类别已存在");
        }
        shoujileibieDao.save(shoujileibie);
    }

    @Override
    public void update(Shoujileibie shoujileibie) {
        shoujileibieDao.save(shoujileibie);
    }

}
