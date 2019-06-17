/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.wetech.admin.szh.xinchou.dao.ToudijishuDao;
import tech.wetech.admin.szh.xinchou.domain.Toudijishu;
import tech.wetech.admin.szh.xinchou.service.ToudijishuService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-17 8:13:29
 */
@Service
public class ToudijishuServiceImpl extends XinchouBaseService<Toudijishu,Long> implements ToudijishuService{

    @Autowired
    ToudijishuDao toudijishuDao;
    
    @Override
    public void add(Toudijishu toudijishu) throws Exception {
        toudijishuDao.save(toudijishu);
    }

    @Override
    public void update(Toudijishu toudijishu) {
        toudijishuDao.save(toudijishu);
    }

}
