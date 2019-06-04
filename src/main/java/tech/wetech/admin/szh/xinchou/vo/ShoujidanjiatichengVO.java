/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.wetech.admin.szh.xinchou.domain.Shoujidanjiaticheng;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-21 10:06:44
 */
@Data
@AllArgsConstructor
public class ShoujidanjiatichengVO {
    
    String name;
    
    Long bumenid;
    
    String bumen;
        
    Long yewuid;
    
    String yewu;
    
    Long kehuid;
    
    String kehu;

    double danjia ;
    
    double ticheng ;

    public ShoujidanjiatichengVO(){
        
    }
    
    public ShoujidanjiatichengVO(Shoujidanjiaticheng shoujidanjiaticheng,String bumen,String yewu,String kehu){
        name = shoujidanjiaticheng.getName();
        bumenid = shoujidanjiaticheng.getBumenid();
        yewuid = shoujidanjiaticheng.getYewuid();
        kehuid = shoujidanjiaticheng.getKehuid();
        danjia = shoujidanjiaticheng.getDanjia();
        ticheng = shoujidanjiaticheng.getTicheng();
        this.bumen = bumen;
        this.yewu = yewu;
        this.kehu = kehu;
    }
}
