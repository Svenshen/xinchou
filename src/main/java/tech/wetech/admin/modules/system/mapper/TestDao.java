/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.modules.system.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.wetech.admin.modules.system.po.Test;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-4-28 12:02:51
 */
@Repository
public interface TestDao extends  JpaRepository<Test,String>{

}
