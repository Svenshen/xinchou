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
import lombok.AllArgsConstructor;
import lombok.Data;
import tech.wetech.admin.szh.xinchou.vo.ShoujiyewuLeibieVO;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-16 8:34:42
 */
@Data
@Entity
@AllArgsConstructor
public class ShoujiyewuLeibie implements Serializable{
    
    @Id
    @Column    
    String shoujiyewu;
    
    @Column
    Long shoujileibieid;
    
    public ShoujiyewuLeibie(ShoujiyewuLeibieVO shoujiyewuLeibieVO){
        shoujiyewu = shoujiyewuLeibieVO.getShoujiyewu();
        shoujileibieid = shoujiyewuLeibieVO.getShoujileibieid();
    }
    
    public ShoujiyewuLeibie(){
        
    }

}
