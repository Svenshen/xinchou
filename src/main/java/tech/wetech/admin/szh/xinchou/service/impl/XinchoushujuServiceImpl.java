/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.wetech.admin.modules.system.dto.TreeDto;
import tech.wetech.admin.modules.system.po.Organization;
import tech.wetech.admin.modules.system.po.User;
import tech.wetech.admin.modules.system.service.OrganizationService;
import tech.wetech.admin.szh.xinchou.dao.XinchoushujuDao;
import tech.wetech.admin.szh.xinchou.domain.Shoujidanjiaticheng;
import tech.wetech.admin.szh.xinchou.domain.ShoujidanjiatichengId;
import tech.wetech.admin.szh.xinchou.domain.Shoujikehuleibie;
import tech.wetech.admin.szh.xinchou.domain.Shoujileibie;
import tech.wetech.admin.szh.xinchou.domain.Toudidanjia;
import tech.wetech.admin.szh.xinchou.domain.ToudidanjiaId;
import tech.wetech.admin.szh.xinchou.domain.Toudijishu;
import tech.wetech.admin.szh.xinchou.domain.Toudishuju;
import tech.wetech.admin.szh.xinchou.domain.Xinchoushuju;
import tech.wetech.admin.szh.xinchou.domain.XinchoushujuId;
import tech.wetech.admin.szh.xinchou.service.ShoujidanjiatichengService;
import tech.wetech.admin.szh.xinchou.service.ShoujikehuleibieService;
import tech.wetech.admin.szh.xinchou.service.ShoujileibieService;
import tech.wetech.admin.szh.xinchou.service.ShoujishujuService;
import tech.wetech.admin.szh.xinchou.service.ToudidanjiaService;
import tech.wetech.admin.szh.xinchou.service.ToudijishuService;
import tech.wetech.admin.szh.xinchou.service.ToudishujuService;
import tech.wetech.admin.szh.xinchou.service.XinchoushujuService;
import tech.wetech.admin.szh.xinchou.vo.ShoujixinchouVO;
import tech.wetech.admin.szh.xinchou.vo.ToudixinchouVO;

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
    @Autowired
    ShoujishujuService shoujishujuService;
    @Autowired
    ShoujidanjiatichengService shoujidanjiatichengService;
    @Autowired
    ShoujileibieService shoujileibieService;
    @Autowired
    ToudishujuService toudishujuService;
    @Autowired
    ToudidanjiaService toudidanjiaService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    ShoujikehuleibieService shoujikehuleibieService;
    @Autowired
    ToudijishuService toudijishuService;
    
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
    
    @Override
    @Transactional
    public void baocunshoujixinchou(Long fanganid,Date kshijian,Date jshijian){
        deleteshoujishuju(fanganid);
        List<ShoujixinchouVO> shoujixinchouVOs = shoujishujuService.queryxinchoushoujilist(kshijian, jshijian);
        List<Xinchoushuju> shoujixinchouVOs1 = new ArrayList();
        for(ShoujixinchouVO shoujixinchouvo:shoujixinchouVOs){
            ShoujidanjiatichengId shoujidanjiatichengId = new ShoujidanjiatichengId();
            shoujidanjiatichengId.setBumenid(shoujixinchouvo.getBumenid());
            
            shoujidanjiatichengId.setKehuid(shoujixinchouvo.getKehuid());
            shoujidanjiatichengId.setYewuid(shoujixinchouvo.getYewuid());
            shoujidanjiatichengId.setName(shoujixinchouvo.getName());
            
            Shoujidanjiaticheng shoujidanjiaticheng = shoujidanjiatichengService.queryById(shoujidanjiatichengId);
            double danjia = 0.0;
            double ticheng = 0.0;
            if(shoujidanjiaticheng != null){
                danjia = shoujidanjiaticheng.getDanjia();
                ticheng = shoujidanjiaticheng.getTicheng();
            }
            double xinchou = 0.0;
            xinchou = shoujixinchouvo.getJianshu()*danjia+shoujixinchouvo.getShouru()*ticheng;
            
            shoujixinchouvo.setBumen(getOrganizationName(shoujixinchouvo.getBumenid()));
            shoujixinchouvo.setYewu(getYewuName(shoujixinchouvo.getYewuid()));
            shoujixinchouvo.setKehu(getKehu(shoujixinchouvo.getKehuid()));
            shoujixinchouvo.setDanjia(danjia);
            shoujixinchouvo.setTicheng(ticheng);
            shoujixinchouvo.setXinchou(xinchou);
            shoujixinchouVOs1.add(new Xinchoushuju(shoujixinchouvo,fanganid));
        }
        saveAndupdateAll(shoujixinchouVOs1);
    }
    
    @Override
    @Transactional
    public void baocuntoudixinchou(Long fanganid,Date kshijian,Date jshijian){
        deletetoudishuju(fanganid);
        List<Xinchoushuju> toudixinchouVOs = new ArrayList();
        for(ToudixinchouVO t :gettoudixinchou(kshijian,jshijian)){
            toudixinchouVOs.add(new Xinchoushuju(t,fanganid));
        }
        saveAndupdateAll(toudixinchouVOs);
    }

//    @Override
//    @Transactional
//    public void saveAndupdateAll(List<Xinchoushuju> listentity) {
//        xinchoushujuDao.saveAll(listentity);
//    }

    private List<ToudixinchouVO> gettoudixinchou(Date kshijian,Date jshijian){
        
        List<ToudixinchouVO> toudixinchouVOs = new ArrayList();
        List<Toudishuju> toudishujus = toudishujuService.getshuju(kshijian, jshijian);
        
        for(Toudishuju toudishuju:toudishujus){
            Toudijishu toudijishu = toudijishuService.queryById(toudishuju.getYewuid());
            int jishu = 0;
            if(toudijishu != null){
                jishu = toudijishu.getJishu();
            }
            toudishuju.setShuliang(toudishuju.getShuliang()-jishu);
            ToudidanjiaId toudidanjiaId = new ToudidanjiaId();
            toudidanjiaId.setBumenid(toudishuju.getBumenid());
            toudidanjiaId.setName(toudishuju.getToudiyuan());
            toudidanjiaId.setYewuid(toudishuju.getYewuid());
            Toudidanjia toudidanjia = toudidanjiaService.queryById(toudidanjiaId);
            double danjia = 0.0;
            if(toudidanjia != null){
                danjia = toudidanjia.getDanjia();
            }
            double xinchou = 0.0;
            xinchou = toudishuju.getShuliang()*danjia;
            ToudixinchouVO toudixinchouVO = new ToudixinchouVO(toudishuju,  getOrganizationName(toudishuju.getBumenid()), getYewuName(toudishuju.getYewuid()), danjia, xinchou);
            toudixinchouVOs.add(toudixinchouVO);
        }
        
        return toudixinchouVOs;
    }
    
    @Override
    public List<ToudixinchouVO> jisuantoudixinchou(Date kshijian, Date jshijian) {
        String xingming = "";
        Long bumen = 0L;
        double xinchouheji = 0.0;
        List<ToudixinchouVO> toudixinchouVOs = new ArrayList();
        for(ToudixinchouVO t :gettoudixinchou(kshijian,jshijian)){
            if(xingming.equals("") && bumen == 0L && xinchouheji == 0.0){
                xingming = t.getName();
                bumen = t.getBumenid();
            }else{
                if(!xingming.equals(t.getName()) || bumen != t.getBumenid()){
                    ToudixinchouVO toudixinchouVO =  new ToudixinchouVO(bumen, getOrganizationName(bumen), xingming, -1L, "合计", 0.0, 0, xinchouheji);
                    toudixinchouVOs.add(toudixinchouVO);
                    xinchouheji = 0.0;
                    xingming = t.getName();
                    bumen = t.getBumenid();
                }
            }
            toudixinchouVOs.add(t);
            xinchouheji += t.getXinchou();
            
        }
        ToudixinchouVO toudixinchouVO =  new ToudixinchouVO(bumen, getOrganizationName(bumen), xingming, -1L, "合计", 0.0, 0, xinchouheji);
        toudixinchouVOs.add(toudixinchouVO);
        return toudixinchouVOs;
    }

    @Override
    public List<ShoujixinchouVO> jisuanshoujixinchou(Date kshijian, Date jshijian) {
        List<ShoujixinchouVO> shoujixinchouVOs = shoujishujuService.queryxinchoushoujilist(kshijian, jshijian);
        List<ShoujixinchouVO> shoujixinchouVOs1 = new ArrayList();
        String xingming = "";
        Long bumen = 0L;
        double xinchouheji = 0.0;
        for(ShoujixinchouVO shoujixinchouvo:shoujixinchouVOs){
            if(xingming.equals("") && bumen == 0L && xinchouheji == 0.0){
                xingming = shoujixinchouvo.getName();
                bumen = shoujixinchouvo.getBumenid();
            }else{
                if(!xingming.equals(shoujixinchouvo.getName()) || bumen != shoujixinchouvo.getBumenid()){
                    ShoujixinchouVO shoujixinchouVO = new ShoujixinchouVO(bumen, getOrganizationName(bumen), xingming, -1L, "合计", -1L, "", 0, 0, 0, 0, xinchouheji);
                    shoujixinchouVOs1.add(shoujixinchouVO);
                    xinchouheji = 0.0;
                    xingming = shoujixinchouvo.getName();
                    bumen = shoujixinchouvo.getBumenid();
                }
            }
            ShoujidanjiatichengId shoujidanjiatichengId = new ShoujidanjiatichengId();
            shoujidanjiatichengId.setBumenid(shoujixinchouvo.getBumenid());
            
            shoujidanjiatichengId.setKehuid(shoujixinchouvo.getKehuid());
            shoujidanjiatichengId.setYewuid(shoujixinchouvo.getYewuid());
            shoujidanjiatichengId.setName(shoujixinchouvo.getName());
            
            Shoujidanjiaticheng shoujidanjiaticheng = shoujidanjiatichengService.queryById(shoujidanjiatichengId);
            double danjia = 0.0;
            double ticheng = 0.0;
            if(shoujidanjiaticheng != null){
                danjia = shoujidanjiaticheng.getDanjia();
                ticheng = shoujidanjiaticheng.getTicheng();
            }
            double xinchou = 0.0;
            xinchou = shoujixinchouvo.getJianshu()*danjia+shoujixinchouvo.getShouru()*ticheng;
            xinchouheji += xinchou;
            shoujixinchouvo.setBumen(getOrganizationName(shoujixinchouvo.getBumenid()));
            shoujixinchouvo.setYewu(getYewuName(shoujixinchouvo.getYewuid()));
            shoujixinchouvo.setKehu(getKehu(shoujixinchouvo.getKehuid()));
            shoujixinchouvo.setDanjia(danjia);
            shoujixinchouvo.setTicheng(ticheng);
            shoujixinchouvo.setXinchou(xinchou);
            
//            ShoujixinchouVO shoujixinchouVO = new ShoujixinchouVO(toudishuju,  getOrganizationName(toudishuju.getBumenid()), getYewuName(toudishuju.getYewuid()), danjia, xinchou);
            shoujixinchouVOs1.add(shoujixinchouvo);
        }
        ShoujixinchouVO shoujixinchouVO = new ShoujixinchouVO(bumen, getOrganizationName(bumen), xingming, -1L, "合计", -1L, "", 0, 0, 0, 0, xinchouheji);
        shoujixinchouVOs1.add(shoujixinchouVO);
        return shoujixinchouVOs1;
    }

    @Override
    public List gerenchaxun(Long fanganid,User user) {
        List<Xinchoushuju> xinchoushujus = findxinchougeren(fanganid, user.getName(),user.getOrganizationId());
        List<Xinchoushuju> xinchoushujus1 = new ArrayList();
        String xingming = "";
        String bumen = "";
        double xinchouheji = 0.0;
        for(Xinchoushuju shoujixinchouvo : xinchoushujus){
            if(xingming.equals("") && bumen.equals("") && xinchouheji == 0.0){
                xingming = shoujixinchouvo.getName();
                bumen = shoujixinchouvo.getBumen();
            }else{
                if(!xingming.equals(shoujixinchouvo.getName()) || !bumen.equals(shoujixinchouvo.getBumen())){
                    Xinchoushuju xinchoushujux = new Xinchoushuju(fanganid, -1L, bumen, xingming, "合计", "", "", 0, 0, 0, 0,  xinchouheji);
                    xinchoushujus1.add(xinchoushujux);
                    xinchouheji = 0.0;
                    xingming = shoujixinchouvo.getName();
                    bumen = shoujixinchouvo.getBumen();
                }
            }
            xinchouheji+= shoujixinchouvo.getXinchou();
            xinchoushujus1.add(shoujixinchouvo);
        }
        Xinchoushuju xinchoushujux = new Xinchoushuju(fanganid, -1L, bumen, xingming, "合计", "", "", 0, 0, 0, 0,  xinchouheji);
        xinchoushujus1.add(xinchoushujux);
        return xinchoushujus1;
    }

    @Override
    public List bumenchaxun(Long fanganid,User user) {
        List<Xinchoushuju> xinchoushujus = finsdxinchoubumen(fanganid, getzibumen(user.getOrganizationId()));
        List<Xinchoushuju> xinchoushujus1 = new ArrayList();
        String xingming = "";
        String bumen = "";
        double xinchouheji = 0.0;
        for(Xinchoushuju shoujixinchouvo : xinchoushujus){
            if(xingming.equals("") && bumen.equals("") && xinchouheji == 0.0){
                xingming = shoujixinchouvo.getName();
                bumen = shoujixinchouvo.getBumen();
            }else{
                if(!xingming.equals(shoujixinchouvo.getName()) || !bumen.equals(shoujixinchouvo.getBumen())){
                    Xinchoushuju xinchoushujux = new Xinchoushuju(fanganid, -1L, bumen, xingming, "合计", "", "", 0, 0, 0, 0,  xinchouheji);
                    xinchoushujus1.add(xinchoushujux);
                    xinchouheji = 0.0;
                    xingming = shoujixinchouvo.getName();
                    bumen = shoujixinchouvo.getBumen();
                }
            }
            xinchouheji+= shoujixinchouvo.getXinchou();
            xinchoushujus1.add(shoujixinchouvo);
        }
        Xinchoushuju xinchoushujux = new Xinchoushuju(fanganid, -1L, bumen, xingming, "合计", "", "", 0, 0, 0, 0,  xinchouheji);
        xinchoushujus1.add(xinchoushujux);
        return xinchoushujus1;
    }
    
    
    private List<Long> getzibumen(Long bumenid){
        List listids = new ArrayList();
        List<TreeDto> list = organizationService.queryOrgTree(bumenid);
        for(TreeDto t : list){
            if(t.isParent()){
                listids.addAll(getzibumen(t.getId()));
            }else{
                listids.add(t.getId());
            }
        }
        return listids;
    }
    
    
    
        public String getOrganizationName(Long organizationId) {
        if(organizationId == null){
            return "";
        }
        Organization organization = organizationService.queryOne(new Organization().setId(organizationId));
        if (organization == null) {
            return "";
        }
        return organization.getName();
    }
    
    public String getYewuName(Long yewuid) {
        if(yewuid == null){
            return "未知业务1";
        }
        Shoujileibie shoujileibie = shoujileibieService.queryById(yewuid);
        if (shoujileibie == null) {
            return "未知业务2";
        }
        return shoujileibie.getName();
    }
    
    public String getKehu(Long kehuid){
        if(kehuid == null){
            kehuid = 1L;
        }
        Shoujikehuleibie shoujikehuleibie =shoujikehuleibieService.queryById(kehuid);
        if(shoujikehuleibie == null){
            return "未知客户";
        }
        return shoujikehuleibie.getName();
    }
}
