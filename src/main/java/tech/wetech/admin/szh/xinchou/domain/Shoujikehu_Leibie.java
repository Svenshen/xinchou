/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.domain;

import java.io.Serializable;
import javax.persistence.Column;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import tech.wetech.admin.szh.xinchou.vo.Shoujikehu_LeibieVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-17 10:38:33
 */
@Data
@AllArgsConstructor
@Entity(name = "shoujikehu_leibie")
public class Shoujikehu_Leibie implements Serializable {

    @Id
    @Column
    String kehudaima;
    
    @Column
    Long leibieid =0L;
    
    public Shoujikehu_Leibie(Shoujikehu_LeibieVO shoujikehu_LeibieVO){
        kehudaima = shoujikehu_LeibieVO.getKehudaima();
        leibieid = shoujikehu_LeibieVO.getLeibieid();
    }
    
    public Shoujikehu_Leibie(){
        
    }
}
