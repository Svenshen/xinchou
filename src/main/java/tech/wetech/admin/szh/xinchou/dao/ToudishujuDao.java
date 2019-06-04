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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.wetech.admin.szh.xinchou.domain.Shoujishuju;
import tech.wetech.admin.szh.xinchou.domain.Toudishuju;
import tech.wetech.admin.szh.xinchou.domain.ToudishujuId;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-7 16:19:37
 */
@Repository
public interface ToudishujuDao extends  JpaRepository<Toudishuju,ToudishujuId>{
    
//    @Query(value = "select * from #{#entityName} where shijian BETWEEN :kshijian and :jshijian order by bumenid,toudiyuan")
//    List<Toudishuju> findByShijian(@Param("kshijian") Date kshijian,@Param("jshijian") Date jshijian);
    
    @Query(value = "select * from #{#entityName} where shijian BETWEEN :kshijian and :jshijian order by bumenid,toudiyuan", nativeQuery = true)
    List<Toudishuju> findByShijian(@Param("kshijian") Date kshijian,@Param("jshijian") Date jshijian);
    
    void deleteByToudiyuan(String toudiyuan);
}
