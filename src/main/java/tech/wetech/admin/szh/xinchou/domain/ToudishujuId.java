/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.domain;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-7 16:06:39
 */

@Data
public class ToudishujuId implements Serializable{

    String toudiyuan;
    Date shijian;
    String toudijigou;
}
