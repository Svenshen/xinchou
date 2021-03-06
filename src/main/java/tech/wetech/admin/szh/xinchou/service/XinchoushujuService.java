/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service;

import java.util.Date;
import java.util.List;
import tech.wetech.admin.modules.system.po.User;
import tech.wetech.admin.szh.xinchou.domain.Xinchoufangan;
import tech.wetech.admin.szh.xinchou.domain.Xinchoushuju;
import tech.wetech.admin.szh.xinchou.domain.XinchoushujuId;
import tech.wetech.admin.szh.xinchou.vo.ShoujixinchouVO;
import tech.wetech.admin.szh.xinchou.vo.ToudixinchouVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-11 8:32:12
 */
public interface XinchoushujuService extends XinchouIService<Xinchoushuju, XinchoushujuId>{

    
    void deleteshoujishuju(Long fangan);
    
    void deletetoudishuju(Long fangan);
    
    List<Xinchoushuju> finsdxinchoubumen(Long fangan,List<Long> bumenids);
    
    List<Xinchoushuju> findxinchougeren(Long fangan,String name, Long bumenid);
    
    void baocunshoujixinchou(Long fanganid,Date kshijian,Date jshijian);
    
    void baocuntoudixinchou(Long fanganid,Date kshijian,Date jshijian);
    
    List<ToudixinchouVO> jisuantoudixinchou(Date kshijian,Date jshijian);
    
    List<ShoujixinchouVO> jisuanshoujixinchou(Date kshijian,Date jshijian);
    
    List gerenchaxun(Long fanganid,User user);
    
    List bumenchaxun(Long fanganid,User user);
}
