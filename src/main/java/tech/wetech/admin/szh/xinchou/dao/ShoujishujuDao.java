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
import tech.wetech.admin.szh.xinchou.domain.Shoujikehu;
import tech.wetech.admin.szh.xinchou.domain.Shoujishuju;
import tech.wetech.admin.szh.xinchou.vo.ShoujixinchouVO;

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
    
    //select shoujiyewu_leibie.shoujileibieid,shoujikehu_leibie.leibieid,shoujishuju.shoujiyuan,shoujishuju.shoujijigou,count(*),sum(shishouyouzi) from shoujishuju left join shoujiyewu_leibie on shoujishuju.chanpin = shoujiyewu_leibie.shoujiyewu left join shoujikehu_leibie on shoujishuju.kehudaima = shoujikehu_leibie.kehudaima group by shoujiyewu_leibie.shoujileibieid,shoujikehu_leibie.leibieid,shoujishuju.shoujiyuan,shoujishuju.shoujijigou order by shoujishuju.shoujijigou,shoujishuju.shoujiyuan
//    @Query(value = "select shoujishuju.shoujijigou as bumenid,shoujishuju.shoujiyuan as name,shoujiyewu_leibie.shoujileibieid as yewuid,shoujikehu_leibie.leibieid as kehuid,count(*) as jianshu,sum(shishouyouzi) as shouru from shoujishuju " +
//                    " left join shoujiyewu_leibie on shoujishuju.chanpin = shoujiyewu_leibie.shoujiyewu " +
//                    " left join shoujikehu_leibie on shoujishuju.kehudaima = shoujikehu_leibie.kehudaima " +
//                    " where shoujishuju.shoujishijian between :kshijian and :jshijian" +
//                    " group by shoujiyewu_leibie.shoujileibieid,shoujikehu_leibie.leibieid,shoujishuju.shoujiyuan,shoujishuju.shoujijigou " +
//                    " order by shoujishuju.shoujijigou,shoujishuju.shoujiyuan",nativeQuery = true)
    @Query(value = "select a.shoujijigou,a.shoujiyuan,b.shoujileibieid,c.leibieid ,count(*) ,sum(shishouyouzi) from #{#entityName} a " +
                    " left join shoujiyewu_leibie b on a.chanpin = b.shoujiyewu " +
                    " left join shoujikehu_leibie c on a.kehudaima = c.kehudaima " +
                    " where a.shoujishijian between :kshijian and :jshijian" +
                    " group by b.shoujileibieid,c.leibieid,a.shoujiyuan,a.shoujijigou " +
                    " order by a.shoujijigou,a.shoujiyuan",nativeQuery = true)        
    List<Object[]> queryshoujixinchoulist(@Param("kshijian") Date kshijian,@Param("jshijian") Date jshijian);
    
    @Transactional
    @Modifying
    @Query(value = "update #{#entityName} set kehudaima = '-1' , kehumingcheng = '散户空白' where kehudaima is null",nativeQuery = true)
    int updatesanhu();
    
    @Query(value = "select daoruid from #{#entityName} group by daoruid")     
    List<String> querydaoruidlist();
    
    @Transactional
    int deleteByDaoruid(String daoruid);
    
    
    
}
