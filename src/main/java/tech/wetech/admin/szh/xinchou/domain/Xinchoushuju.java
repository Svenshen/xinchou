/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import tech.wetech.admin.szh.xinchou.vo.ShoujixinchouVO;
import tech.wetech.admin.szh.xinchou.vo.ToudixinchouVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-8 16:53:38
 */
@Data
@Entity
@AllArgsConstructor
@IdClass(XinchoushujuId.class)
public class Xinchoushuju implements Serializable{
    
    @Id
    @Column
    Long fanganid;    
    
    @Id
    @Column
    Long bumenid;
    
    @Id
    @Column(length = 50)
    String bumen;
    
    @Id
    @Column(length = 50)
    String name;
    
    @Id
    @Column(length = 50)
    String zhonglei;
    
    @Id
    @Column(length = 50)
    String yewu;
    
    @Id
    @Column(length = 50)
    String kehu;
    
    @Column
    double danjia;
    
    @Column
    int jianshu ;
    
    @Column
    double ticheng;
    
    @Column
    double shouru ;

    @Column
    double xinchou;
    
    public Xinchoushuju(){
        
    }
    
    public Xinchoushuju(ShoujixinchouVO shoujixinchouVO,Long fanganid){
        this.fanganid = fanganid;
        this.bumenid = shoujixinchouVO.getBumenid();
        this.bumen = shoujixinchouVO.getBumen();
        this.name = shoujixinchouVO.getName();
        this.yewu = shoujixinchouVO.getYewu();
        this.kehu = shoujixinchouVO.getKehu();
        this.zhonglei = "收寄";
        this.danjia = shoujixinchouVO.getDanjia();
        this.jianshu = shoujixinchouVO.getJianshu();
        this.ticheng = shoujixinchouVO.getTicheng();
        this.shouru = shoujixinchouVO.getShouru();
        this.xinchou = shoujixinchouVO.getXinchou();
        
    }

    public Xinchoushuju(ToudixinchouVO toudixinchouVO,Long fanganid){
        this.fanganid = fanganid;
        this.bumenid = toudixinchouVO.getBumenid();
        this.bumen = toudixinchouVO.getBumen();
        this.name = toudixinchouVO.getName();
        this.yewu = toudixinchouVO.getYewu();
        this.kehu = "";
        this.zhonglei = "投递";
        this.danjia = toudixinchouVO.getDanjia();
        this.jianshu = toudixinchouVO.getJianshu();
        this.ticheng = 0.0;
        this.shouru = 0.0;
        this.xinchou = toudixinchouVO.getXinchou();
    }
}
