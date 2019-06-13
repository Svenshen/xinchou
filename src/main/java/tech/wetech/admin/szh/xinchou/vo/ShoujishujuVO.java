/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Data;
import tech.wetech.admin.szh.xinchou.domain.Shoujishuju;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-13 12:57:11
 */
@Data
@AllArgsConstructor
public class ShoujishujuVO {
    
    public ShoujishujuVO(){
        
    }
    
    public ShoujishujuVO(Shoujishuju shoujishuju,String bumen){
        this.bumen = bumen;
        chanpin = shoujishuju.getChanpin();
        youjianhao = shoujishuju.getYoujianhao();
        jidaguo = shoujishuju.getJidaguo();
        jidasheng = shoujishuju.getJidasheng();
        jidashi = shoujishuju.getJidashi();
        jidaju = shoujishuju.getJidaju();
        kehudaima = shoujishuju.getKehudaima();
        kehumingcheng = shoujishuju.getKehumingcheng();
        shoujiyuan = shoujishuju.getShoujiyuan();
        shoujishijian = shoujishuju.getShoujishijian();
    }

    
    String chanpin;
        
    String youjianhao;
     
    String jidaguo;
    
    String jidasheng;
    
    String jidashi;
    
    String jidaju;
    
    String kehudaima;
    
    String kehumingcheng;
    
    String shoujiyuan;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    Date shoujishijian;
    
    String bumen;
}
