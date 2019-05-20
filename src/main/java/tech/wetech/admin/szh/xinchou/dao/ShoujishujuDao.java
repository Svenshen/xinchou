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
import org.springframework.stereotype.Repository;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehu;
import tech.wetech.admin.szh.xinchou.domain.Shoujishuju;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-7 15:42:53
 */
@Repository
public interface ShoujishujuDao extends  JpaRepository<Shoujishuju,String>{

    @Query(value = "select chanpin from #{#entityName} group by chanpin")
    List<String> queryyewulist();
    
    @Query(value = "select new tech.wetech.admin.szh.xinchou.domain.Shoujikehu(kehudaima,kehumingcheng) from #{#entityName} where kehudaima is not null group by kehudaima,kehumingcheng")
    List<Shoujikehu> querykehulist();
    
}
