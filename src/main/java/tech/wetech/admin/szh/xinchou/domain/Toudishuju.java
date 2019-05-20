/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-7 16:04:05
 */
@Data
@Entity
@IdClass(ToudishujuId.class)
public class Toudishuju implements Serializable{
    @Excel(name = "投递员")
    @Id
    @Column
    String toudiyuan;
    
    @Id
    @Column
    String toudijigou;
    
    @Id
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date shijian;
    
    @Column
    String yewu;
    
//    @Excel(name = "商函平信")
//    @Column
//    String shanghanpingxin;
//    
//    @Excel(name = "快递包裹")
//    @Column
//    String kuaidibaoguo;
//    
//    @Excel(name = "国际包裹")
//    @Column
//    String guojibaoguo;
//    
//    @Excel(name = "标准快递")
//    @Column
//    String biaozhunkuaidi;
//    
//    @Excel(name = "国际特快")
//    @Column
//    String guojitekuai;
//    
//    @Excel(name = "港澳特快")
//    @Column
//    String gangaotekuai;
    
    @Excel(name = "合计")
    @Column
    Integer shuliang;
    
    
    
}
