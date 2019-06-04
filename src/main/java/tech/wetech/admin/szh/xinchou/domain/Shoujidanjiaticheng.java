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
import tech.wetech.admin.szh.xinchou.vo.ShoujidanjiatichengVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-21 9:57:52
 */
@Data
@AllArgsConstructor
@Entity
@IdClass(ShoujidanjiatichengId.class)
public class Shoujidanjiaticheng implements Serializable{
    
    @Id
    @Column
    String name;
    
    @Id
    @Column
    Long bumenid;
    
    @Id
    @Column
    Long yewuid;
    
    @Id
    @Column
    Long kehuid;

    @Column
    double danjia = 0.0;
    
    @Column
    double ticheng = 0.0;
    
    public Shoujidanjiaticheng(){
        
    }
    
    public Shoujidanjiaticheng(ShoujidanjiatichengVO shoujidanjiatichengVO){
        name = shoujidanjiatichengVO.getName();
        bumenid = shoujidanjiatichengVO.getBumenid();
        yewuid = shoujidanjiatichengVO.getYewuid();
        kehuid = shoujidanjiatichengVO.getKehuid();
        danjia = shoujidanjiatichengVO.getDanjia();
        ticheng = shoujidanjiatichengVO.getTicheng();
        
    }
    
}
