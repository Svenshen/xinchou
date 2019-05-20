/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-16 16:40:18
 */
@Data
@Entity
@AllArgsConstructor
public class Shoujikehu implements Serializable {
    
    @Id
    @Column
    String kehudaima;
    
    @Column
    String kehumingcheng;

    
    public Shoujikehu(){
        
    }
}
