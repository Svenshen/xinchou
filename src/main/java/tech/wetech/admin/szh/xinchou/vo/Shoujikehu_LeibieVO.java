/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.vo;

import tech.wetech.admin.szh.xinchou.domain.*;
import java.io.Serializable;
import javax.persistence.Column;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-17 10:38:33
 */
@Data
@AllArgsConstructor
public class Shoujikehu_LeibieVO  {

    String kehudaima;
    
    String kehumingcheng;
    
    Long leibieid;
    
    String leibiemingcheng;
    
    public Shoujikehu_LeibieVO(Shoujikehu_Leibie shoujikehu_Leibie){
        kehudaima = shoujikehu_Leibie.getKehudaima();
        leibieid = shoujikehu_Leibie.getLeibieid();
    }
    
    public Shoujikehu_LeibieVO(){
        
    }
    
}
