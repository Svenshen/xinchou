/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.vo;

import javax.annotation.Resource;
import javax.annotation.Resources;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import tech.wetech.admin.modules.system.mapper.OrganizationMapper;
import tech.wetech.admin.szh.xinchou.domain.Toudidanjia;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-13 17:11:25
 */
@Data
@AllArgsConstructor
public class ToudidanjiaVO {
    
    String name;
    
    Long bumenid;
    
    String bumen;
    
    Long yewuid;
    
    String yewu;
    
    double danjia;
    

    
    public  ToudidanjiaVO(Toudidanjia toudidanjia,String bumen,String yewu){
        bumenid = toudidanjia.getBumenid();
        name = toudidanjia.getName();
        yewuid = toudidanjia.getYewuid();
        danjia = toudidanjia.getDanjia();
        this.bumen = bumen;
        this.yewu = yewu;
    }

    public  ToudidanjiaVO(){
        
    }
}
