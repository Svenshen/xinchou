/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.wetech.admin.szh.xinchou.domain.Toudishuju;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-21 16:42:45
 */
@Data
@AllArgsConstructor
public class ToudixinchouVO {
    
    Long bumenid;
    String bumen;
    
    String name;
    
    Long yewuid;
    String yewu;
    
    double danjia;
    int jianshu;
    
    double xinchou;
    
    public ToudixinchouVO(){
        
    }
    
    
    public ToudixinchouVO(Toudishuju toudishuju,String bumen,String yewu,double danjia,double xinchou){
        bumenid = toudishuju.getBumenid();
        this.bumen = bumen;
        name = toudishuju.getToudiyuan();
        yewuid =toudishuju.getYewuid();
        this.yewu = yewu;
        this.danjia = danjia;
        jianshu = toudishuju.getShuliang();
        this.xinchou = xinchou;
    }

}
