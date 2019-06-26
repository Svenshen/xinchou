/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.domain;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-8 18:14:16
 */
@Data
public class XinchoushujuId implements Serializable{
    
    Long fanganid;    
    
    Long bumenid;
    
    String bumen;
    
    String name;
    
    String zhonglei;
    
    String yewu;

    String kehu;
}
