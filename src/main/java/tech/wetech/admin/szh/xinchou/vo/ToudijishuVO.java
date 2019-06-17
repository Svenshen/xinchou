/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import tech.wetech.admin.szh.xinchou.domain.Toudijishu;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-17 8:28:31
 */
@Data
@AllArgsConstructor
public class ToudijishuVO {
    
    public ToudijishuVO(Toudijishu toudijishu,String yewu){
        id = toudijishu.getId();
        this.yewu = yewu;
        jishu = toudijishu.getJishu();
    }
    
    public ToudijishuVO(){
        
    }
    
    Long id;
    
    String yewu;
    
    Integer jishu;

}
