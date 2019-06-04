/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service;

import tech.wetech.admin.szh.xinchou.domain.Xinchoufangan;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-23 8:29:52
 */
public interface XinchoufanganService extends XinchouIService<Xinchoufangan, Long>{

    void add(Xinchoufangan xinchoufangan) throws Exception;
    
    void update(Xinchoufangan xinchoufangan);
    
    void delete(Xinchoufangan xinchoufangan);
}
