/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.modules.system.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-4-28 11:48:13
 */
@Data
@Entity
public class Test implements Serializable{
    @Id
    @Column
    String jigouhao;
    @Column
    String bumen;

}
