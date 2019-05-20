/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.wetech.admin.szh.xinchou.domain.Toudiyewu;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-9 14:12:29
 */
@Repository
public interface ToudiyewuDao  extends  JpaRepository<Toudiyewu,Long>{
    

}
