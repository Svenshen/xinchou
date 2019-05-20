/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.wetech.admin.szh.xinchou.dao.ShoujishujuDao;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehu;
import tech.wetech.admin.szh.xinchou.domain.Shoujishuju;
import tech.wetech.admin.szh.xinchou.service.ShoujishujuService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-5-7 14:52:21
 */
@Service
public class ShoujishujuServiceImpl extends XinchouBaseService<Shoujishuju,String> implements ShoujishujuService{
    
    @Autowired
    ShoujishujuDao shoujishujuDao;

    @Override
    public void exceldaoru(MultipartFile file,String bumen) throws IOException, Exception{
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        List<Shoujishuju>  listshoujishuju =ExcelImportUtil.importExcel(file.getInputStream(), Shoujishuju.class, params);
        listshoujishuju.forEach((j) -> {
            j.setShoujijigou(bumen);
        });
        shoujishujuDao.saveAll(listshoujishuju);
    }

    @Override
    public List<String> queryyewulist() {
        return shoujishujuDao.queryyewulist();
    }

    @Override
    public List<Shoujikehu> querykehulist() {
        return shoujishujuDao.querykehulist();
    }

    
    
}
