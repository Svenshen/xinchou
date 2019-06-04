/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.wetech.admin.szh.xinchou.dao.XinchoufanganDao;
import tech.wetech.admin.szh.xinchou.domain.Xinchoufangan;
import tech.wetech.admin.szh.xinchou.service.XinchoufanganService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-23 8:30:45
 */
@Service
public class XinchoufanganServiceImpl extends XinchouBaseService<Xinchoufangan,Long> implements XinchoufanganService{

    @Autowired
    XinchoufanganDao xinchoufanganDao;
    
    
    
    @Override
    public void add(Xinchoufangan xinchoufangan) throws Exception {
        if(queryOne(xinchoufangan) != null){
            throw new Exception("方案已存在");
        }
        xinchoufanganDao.save(xinchoufangan);
    }

    @Override
    public void update(Xinchoufangan xinchoufangan) {
        xinchoufanganDao.save(xinchoufangan);
    }
    
    @Override
    @Transactional
    public void delete(Xinchoufangan xinchoufangan) {
        xinchoufanganDao.delete(xinchoufangan);
    }

}
