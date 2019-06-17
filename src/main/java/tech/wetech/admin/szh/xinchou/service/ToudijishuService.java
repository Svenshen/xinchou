/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service;

import tech.wetech.admin.szh.xinchou.domain.Toudijishu;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-17 8:11:43
 */
public interface ToudijishuService extends XinchouIService<Toudijishu,Long>{
    
    void add(Toudijishu toudijishu) throws Exception;
    
    void update(Toudijishu toudijishu);    

}
