/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.wetech.admin.szh.xinchou.domain.Shoujishuju;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-4 9:09:18
 */
@Data
@AllArgsConstructor
public class ShoujixinchouVO {
    
    Long bumenid;
    
    String bumen;
    
    String name;
        
    Long yewuid;
    
    String yewu;
    
    Long kehuid;
    
    String kehu;
    

    double danjia;
    
    int jianshu ;
        
    double ticheng;
    
    double shouru ;

    double xinchou;
    
    public ShoujixinchouVO(){
        
    }
    
    public ShoujixinchouVO(Long bumenid,String name,Long yewuid,Long kehuid,int jianshu,double shouru){
        this.bumenid = bumenid;
        this.name = name;
        this.yewuid = yewuid;
        this.kehuid = kehuid;
        this.jianshu = jianshu;
        this.shouru = shouru;
    }
    
    /*public ShoujixinchouVO(ShoujixinchouVO shoujixinchouVO,String bumen,String yewu,String kehu,double danjia,double ticheng,double xinchou){
        
        shoujixinchouVO.setBumen(bumen);
        shoujixinchouVO.setYewu(yewu);
        shoujixinchouVO.setKehu(kehu);
        shoujixinchouVO.setDanjia(danjia);
        shoujixinchouVO.setTicheng(ticheng);
        shoujixinchouVO.setXinchou(xinchou);
    }*/
    
    
}
