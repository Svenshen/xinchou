/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.wetech.admin.szh.xinchou.domain.ShoujiyewuLeibie;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-16 8:53:24
 */
@Data
@AllArgsConstructor
public class ShoujiyewuLeibieVO {
    
       
    String shoujiyewu;
    
    
    Long shoujileibieid;
    
    String shoujileibie;
    
    public ShoujiyewuLeibieVO(ShoujiyewuLeibie shoujiyewuLeibie){
        shoujiyewu = shoujiyewuLeibie.getShoujiyewu();
        shoujileibieid = shoujiyewuLeibie.getShoujileibieid();
    }
    
    public ShoujiyewuLeibieVO(){
        
    }
   

}
