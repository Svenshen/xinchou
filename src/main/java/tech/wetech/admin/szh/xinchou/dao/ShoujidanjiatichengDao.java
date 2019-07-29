/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.wetech.admin.szh.xinchou.domain.Shoujidanjiaticheng;
import tech.wetech.admin.szh.xinchou.domain.ShoujidanjiatichengId;
import tech.wetech.admin.szh.xinchou.domain.Toudidanjia;
import tech.wetech.admin.szh.xinchou.domain.ToudidanjiaId;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-21 10:00:42
 */
@Repository
public interface ShoujidanjiatichengDao extends JpaRepository<Shoujidanjiaticheng,ShoujidanjiatichengId>{

    @Query(value = "select * from #{#entityName} where  bumenid in (:bumenids) ",nativeQuery = true)
    List<Shoujidanjiaticheng> findbybumenlist(@Param("bumenids") List<Long> bumenids);
    
}
