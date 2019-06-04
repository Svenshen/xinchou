/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.wetech.admin.szh.xinchou.domain.Xinchoufangan;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-23 8:27:08
 */
@Repository
public interface XinchoufanganDao extends  JpaRepository<Xinchoufangan,Long>{

}
