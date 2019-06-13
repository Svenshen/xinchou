/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import lombok.AllArgsConstructor;
import lombok.Data;
import tech.wetech.admin.szh.xinchou.domain.Toudishuju;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-13 14:53:40
 */
@Data
@AllArgsConstructor
public class ToudishujuVO {

    public ToudishujuVO(){
        
    }
    
    public ToudishujuVO(Toudishuju toudishuju,String yewu,String bumen){
        this.yewu = yewu;
        this.bumen =bumen;
        toudiyuan = toudishuju.getToudiyuan();
        shijian = toudishuju.getShijian();
        shuliang = toudishuju.getShuliang();
    }
    
    String toudiyuan;
    
    String bumen;
    
    @JsonFormat(pattern="yyyy-MM")
    Date shijian;
    
    String yewu;
    
    Integer shuliang;
}
