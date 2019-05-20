/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service;

import tech.wetech.admin.szh.xinchou.domain.Shoujileibie;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-15 17:18:18
 */
public interface ShoujileibieService extends XinchouIService<Shoujileibie, Long>{

    void add(Shoujileibie shoujileibie) throws Exception;
    
    void update(Shoujileibie shoujileibie);    
}
