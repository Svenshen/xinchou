/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service;

import java.util.List;
import tech.wetech.admin.szh.xinchou.domain.Shoujidanjiaticheng;
import tech.wetech.admin.szh.xinchou.domain.ShoujidanjiatichengId;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehu;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-21 10:01:30
 */
public interface ShoujidanjiatichengService extends XinchouIService<Shoujidanjiaticheng, ShoujidanjiatichengId>{

    void update(List<Shoujidanjiaticheng> shoujidanjiatichengs);
            
    void update(Shoujidanjiaticheng shoujidanjiaticheng);
}
