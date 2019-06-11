/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.wetech.admin.szh.xinchou.dao.XinchoushujuDao;
import tech.wetech.admin.szh.xinchou.domain.Xinchoushuju;
import tech.wetech.admin.szh.xinchou.domain.XinchoushujuId;
import tech.wetech.admin.szh.xinchou.service.XinchoushujuService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-6-11 8:33:10
 */
@Service
public class XinchoushujuServiceImpl extends XinchouBaseService<Xinchoushuju,XinchoushujuId> implements XinchoushujuService{

    @Autowired
    XinchoushujuDao xinchoushujuDao;
    
    @Override
    public void deleteshoujishuju(Long fangan) {
        xinchoushujuDao.deleteshuju("收寄", fangan);
    }

    @Override
    public void deletetoudishuju(Long fangan) {
        xinchoushujuDao.deleteshuju("投递", fangan);
    }

    @Override
    public List<Xinchoushuju> finsdxinchoubumen(Long fangan, List<Long> bumenids) {
        return xinchoushujuDao.findxinchoubumen(fangan,bumenids);
    }

    @Override
    public List<Xinchoushuju> findxinchougeren(Long fangan, String name, Long bumenid) {
        return xinchoushujuDao.findxinchougeren(fangan, name, bumenid);
    }

}
