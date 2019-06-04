/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tech.wetech.admin.szh.xinchou.dao.ToudishujuDao;
import tech.wetech.admin.szh.xinchou.domain.Toudishuju;
import tech.wetech.admin.szh.xinchou.domain.ToudishujuId;
import tech.wetech.admin.szh.xinchou.service.ToudishujuService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-7 16:20:37
 */
@Service
public class ToudishujuServiceImpl extends XinchouBaseService<Toudishuju,ToudishujuId> implements ToudishujuService{
    
    @Autowired
    ToudishujuDao toudishujuDao;

    @Override
    public void exceldaoru(MultipartFile file, Long bumen,Long yewu,Date shijian) throws IOException, Exception {
        Calendar cal=Calendar.getInstance();
        cal.setTime(shijian);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        shijian = cal.getTime();
        ImportParams params = new ImportParams();
        params.setTitleRows(2);
        params.setHeadRows(1);
        List<Toudishuju>  listtoudishuju =ExcelImportUtil.importExcel(file.getInputStream(), Toudishuju.class, params);
        for(Toudishuju j : listtoudishuju){
            j.setBumenid(bumen);
            j.setYewuid(yewu);
            j.setShijian(shijian);
        }
        toudishujuDao.saveAll(listtoudishuju);
    }

    @Override
    public List<Toudishuju> getshuju(Date kshijian, Date jshijian) {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return toudishujuDao.findByShijian(kshijian, jshijian);
        //return toudishujuDao.findByShijian(sdf.format(kshijian), sdf.format(jshijian));
    }

    @Override
    @Transactional
    public void deleteheji() {
        toudishujuDao.deleteByToudiyuan("合计");
    }
    
    

}
