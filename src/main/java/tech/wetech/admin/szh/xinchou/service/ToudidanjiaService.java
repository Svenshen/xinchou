/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service;

import java.util.List;
import tech.wetech.admin.modules.system.po.User;
import tech.wetech.admin.szh.xinchou.domain.Toudidanjia;
import tech.wetech.admin.szh.xinchou.domain.ToudidanjiaId;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-13 14:48:02
 */
public interface ToudidanjiaService extends XinchouIService<Toudidanjia,ToudidanjiaId>{

        
    void update(List<Toudidanjia> toudidanjias);
            
    void update(Toudidanjia toudidanjia);
    
    List<Toudidanjia> findbybumenList(User user);
}
