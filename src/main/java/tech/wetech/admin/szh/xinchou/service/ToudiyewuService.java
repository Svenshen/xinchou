/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service;

import java.util.List;
import tech.wetech.admin.szh.xinchou.domain.Toudiyewu;

/**
 *
 * @author  szh
 * QQ:873689
 * @param <T>
 * @param <ID>
 * @date 2019-5-9 14:13:41
 */

public interface ToudiyewuService extends XinchouIService<Toudiyewu, Long>{
    
    void add(Toudiyewu toudiyewu) throws Exception;
    
    void update(Toudiyewu toudiyewu);    

}
