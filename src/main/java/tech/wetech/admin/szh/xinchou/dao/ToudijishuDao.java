/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.wetech.admin.szh.xinchou.domain.Toudijishu;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-17 8:10:53
 */
@Repository
public interface ToudijishuDao extends JpaRepository<Toudijishu,Long>{

}
