/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import tech.wetech.admin.szh.xinchou.domain.Shoujishuju;
import java.util.List;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-7 14:52:04
 */
public interface ShoujishujuService  extends XinchouIService<Shoujishuju, String>{

    void exceldaoru(MultipartFile file,String bumen) throws IOException,Exception;
    
    List queryyewulist();
    
    List querykehulist();
}
