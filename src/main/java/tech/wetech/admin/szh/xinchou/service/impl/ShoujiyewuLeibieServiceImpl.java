/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.wetech.admin.szh.xinchou.dao.ShoujiyewuLeibieDao;
import tech.wetech.admin.szh.xinchou.domain.ShoujiyewuLeibie;
import tech.wetech.admin.szh.xinchou.service.ShoujiyewuLeibieService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-16 8:42:09
 */
@Service
public class ShoujiyewuLeibieServiceImpl extends XinchouBaseService<ShoujiyewuLeibie,String> implements ShoujiyewuLeibieService{
    
    @Autowired
    ShoujiyewuLeibieDao shoujiyewuLeibieDao;
    
    

}
