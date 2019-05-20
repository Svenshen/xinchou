/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service;

import tech.wetech.admin.szh.xinchou.domain.Shoujikehuleibie;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-16 16:57:23
 */
public interface ShoujikehuleibieService extends XinchouIService<Shoujikehuleibie, Long>{

    void add(Shoujikehuleibie shoujikehuleibie) throws Exception;
    
    void update(Shoujikehuleibie shoujikehuleibie);

    void deletekehu(Shoujikehuleibie shoujikehuleibie)throws Exception;
}
