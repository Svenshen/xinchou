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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.wetech.admin.szh.xinchou.dao.ShoujishujuDao;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehu;
import tech.wetech.admin.szh.xinchou.domain.Shoujishuju;
import tech.wetech.admin.szh.xinchou.service.ShoujishujuService;
import tech.wetech.admin.szh.xinchou.vo.ShoujixinchouVO;

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
    public void exceldaoru(MultipartFile file,String bumen,String daoruid) throws IOException, Exception{
        ImportParams params = new ImportParams();
        
        params.setTitleRows(0);
        params.setHeadRows(1);
        List<Shoujishuju>  listshoujishuju =ExcelImportUtil.importExcel(file.getInputStream(), Shoujishuju.class, params);
        listshoujishuju.forEach((j) -> { 
            if(j.getYoujianhao() == null || "null".equals(j.getYoujianhao())){
                    listshoujishuju.remove(j);
                }
        });
        
        listshoujishuju.forEach((j) -> {            
            j.setShoujijigou(bumen);
            j.setDaoruid(daoruid);
        });
        shoujishujuDao.saveAll(listshoujishuju);
        updatesanhu();
    }

    @Override
    public List<String> queryyewulist() {
        return shoujishujuDao.queryyewulist();
    }

    @Override
    public List<Shoujikehu> querykehulist() {
        return shoujishujuDao.querykehulist();
    }

    @Override
    public List<ShoujixinchouVO> queryxinchoushoujilist(Date kshijian,Date jshijian) {
        List<Object[]> objects = shoujishujuDao.queryshoujixinchoulist(kshijian,jshijian);
        List<ShoujixinchouVO> shoujixinchouVOs = new ArrayList();
        for(Object[] b : objects){
            for(int i = 0;i < b.length;i++){
                if(b[i] == null || "null".equals(b[i])){
                    b[i] = "0";
                }
            }
            
            ShoujixinchouVO s = new ShoujixinchouVO();
            s.setBumenid(Long.valueOf(String.valueOf(b[0])));
            s.setName(String.valueOf(b[1]));
            s.setYewuid(Long.valueOf(String.valueOf(b[2])));
            s.setKehuid(Long.valueOf(String.valueOf(b[3])));
            s.setJianshu(Integer.valueOf(String.valueOf(b[4])));
            s.setShouru(Double.valueOf(String.valueOf(b[5])));
            shoujixinchouVOs.add(s);
        }
        
        
        
        return shoujixinchouVOs;
    }

    @Override
    
    public void updatesanhu() {
        shoujishujuDao.updatesanhu();
    }

    @Override
    public List<String> querydaoruidlist() {
        return shoujishujuDao.querydaoruidlist();
    }

    @Override
    public void deletedaoruid(String daoruid) {
        shoujishujuDao.deleteByDaoruid(daoruid);
    }

    
    
}
