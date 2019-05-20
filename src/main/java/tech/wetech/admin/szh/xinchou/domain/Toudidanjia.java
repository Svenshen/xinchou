/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import tech.wetech.admin.szh.xinchou.vo.ToudidanjiaVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-13 14:37:29
 */
@Data
@AllArgsConstructor
@Entity
@IdClass(ToudidanjiaId.class)
public class Toudidanjia implements Serializable{
    
    @Id
    @Column
    String name;
    
    @Id
    @Column
    Long bumenid;
    
    @Id
    @Column
    Long yewuid;
    
    @Column
    double danjia = 0.0;
    
    public Toudidanjia(){
        
    }
    public Toudidanjia(ToudidanjiaVO toudidanjiaVO){
        bumenid = toudidanjiaVO.getBumenid();
        danjia = toudidanjiaVO.getDanjia();
        name = toudidanjiaVO.getName();
        yewuid = toudidanjiaVO.getYewuid();
    }
    
}
