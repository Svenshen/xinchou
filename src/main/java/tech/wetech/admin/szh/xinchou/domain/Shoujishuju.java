/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2018-7-31 8:30:55
 */
@Data
@Entity

public class Shoujishuju implements Serializable {
    
    @Excel(name = "可售卖产品")
    @Column
    String chanpin;
    
    @Excel(name = "邮件号")
    @Id
    @Column
    String youjianhao;
    
    @Excel(name = "寄件人")
    @Column
    String jijianren;
    
    @Excel(name = "寄达国（地区）")
    @Column
    String jidaguo;
    
    @Excel(name = "寄达省名称")
    @Column
    String jidasheng;
    
    @Excel(name = "寄达市名称")
    @Column
    String jidashi;
    
    @Excel(name = "寄达局")
    @Column
    String jidaju;
    
    @Excel(name = "大宗客户代码")
    @Column
    String kehudaima;
    
    @Excel(name = "大宗客户名称")
    @Column
    String kehumingcheng;
    
    @Excel(name = "子客户编码")
    @Column
    String zikehubianma;
    
    @Excel(name = "子客户名称")
    @Column
    String zikehumingcheng;
    
    @Excel(name = "收寄员")
    @Column
    String shoujiyuan;
    
    @Excel(name = "收寄时间",format = "yyyy-MM-dd HH:mm:ss")
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    Date shoujishijian;
    
    @Excel(name = "运输方式")
    @Column
    String yunshufangshi;
    
    @Excel(name = "本机构格口")
    @Column
    String benjigougekou;
    
    @Excel(name = "四级分拣码")
    @Column
    String sijifenjianma;
    
    @Excel(name = "重量(克)")
    @Column
    String zhongliang;
    
    @Excel(name = "计费重量(克)")
    @Column
    String jifeizhongliang;
    
    @Excel(name = "总邮资")
    @Column
    String zongyouzi;
    
    @Excel(name = "标准邮资")
    @Column
    String biaozhunyouzi;
    
    @Excel(name = "实收邮资")
    @Column
    String shishouyouzi;
    
    @Excel(name = "其他邮资")
    @Column
    String qitayouzi;
    
    @Excel(name = "回单金额")
    @Column
    String huidanjine;
    
    @Excel(name = "保费金额")
    @Column
    String baofeijinge;
    
    @Excel(name = "结算方式")
    @Column
    String jiesuanfangshi;
    
    @Excel(name = "收寄来源")
    @Column
    String shoujilaiyuan;
    
    @Excel(name = "代收款")
    @Column
    String daishoukuan;
    
    @Excel(name = "体积")
    @Column
    String tiji;
    
    @Excel(name = "长")
    @Column
    String chang;
    
    @Excel(name = "宽")
    @Column
    String kuan;
    
    @Excel(name = "高")
    @Column
    String gao;
    
    @Excel(name = "保价保险标志")
    @Column
    String baojiabaoxianbiaozhi;
    
    @Excel(name = "声明价值")
    @Column
    String shengmingjiazhi;
    
    @Excel(name = "回单标志")
    @Column
    String huidanbiaozhi;
    
    @Excel(name = "回单运单号")
    @Column
    String huidanyundanhao;
    
    @Excel(name = "一票多件主单号")
    @Column
    String yipiaoduojianzhudanhao;
    
    @Excel(name = "一票多件标识 ")
    @Column
    String yipiaoduojianbiaozhi;
    
    @Excel(name = "一票多件计费方式 ")
    @Column
    String yipiaoduojianjifeifangshi;
    
    @Excel(name = "包装")
    @Column
    String baozhuang;
    
    @Excel(name = "最后修改时间",format = "yyyy-MM-dd")
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    Date zuihouxiugaishijian;
    
    
    @Column
    String shoujijigou;
    
    @Column
    String daoruid;
    
 
    
}
