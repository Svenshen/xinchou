/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.dao;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tech.wetech.admin.szh.xinchou.domain.Xinchoushuju;
import tech.wetech.admin.szh.xinchou.domain.XinchoushujuId;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-11 8:29:57
 */
@Repository
public interface XinchoushujuDao extends  JpaRepository<Xinchoushuju,XinchoushujuId>{
    
    
    @Transactional
    @Modifying
    @Query(value = "delete from #{#entityName} where fanganid = :fangan and zhonglei = :zhonglei",nativeQuery = true)
    int deleteshuju(@Param("zhonglei")String zhonglei,@Param("fangan")Long fangan);
    
    @Query(value = "select * from #{#entityName} where fanganid = :fangan and bumenid in (:bumenids)",nativeQuery = true)
    List<Xinchoushuju> findxinchoubumen(@Param("fangan")Long fangan,@Param("bumenids") List<Long> bumenids);
    
    @Query(value = "select * from #{#entityName} where fanganid = :fangan and name = :name and bumenid = :bumenid",nativeQuery = true)
    List<Xinchoushuju> findxinchougeren(@Param("fangan")Long fangan,@Param("name") String name,@Param("bumenid") Long bumenid);
    
}
