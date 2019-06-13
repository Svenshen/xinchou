/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import tech.wetech.admin.szh.xinchou.domain.Toudishuju;
import tech.wetech.admin.szh.xinchou.domain.ToudishujuId;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-7 16:20:23
 */
public interface ToudishujuService extends XinchouIService<Toudishuju,ToudishujuId>{

    
    public void exceldaoru(MultipartFile file,Long bumen,Long yewu,Date shijian,String daoruid) throws IOException,Exception;
    
    List<Toudishuju> getshuju(Date kshijian,Date jshijian);
    
    void deleteheji();
    
    List querydaoruidlist();
    
    void deletedaoruid(String daoruid);
}
