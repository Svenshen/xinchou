/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.wetech.admin.modules.base.query.PageQuery;
import tech.wetech.admin.szh.xinchou.domain.Toudiyewu;
import tech.wetech.admin.szh.xinchou.service.XinchouIService;

/**
 *
 * @author  szh
 * QQ:873689
 * @param <T>
 * @param <ID>
 * @date 2019-5-9 15:21:51
 */
public abstract class XinchouBaseService<T,ID> implements XinchouIService<T,ID>{

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    protected JpaRepository<T,ID> dao;
    
    @Override
    public List<T> queryAll() {
        return dao.findAll();
    }

    @Override
    public List<T> queryList(T entity) {
        Example<T> e = Example.of(entity);
        return dao.findAll(e);
    }

    @Override
    public T queryOne(T entity) {
        Example<T> e = Example.of(entity);
        return dao.findOne(e).orElse(null);
    }

    @Override
    public T queryById(ID id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Page<T> queryList(T entity, Pageable pageable) {
        Example<T> e = Example.of(entity);
        return dao.findAll(e, pageable);
    }

    @Override
    public void deleteById(ID id) {
        dao.deleteById(id);
    }

    @Override
    public long count(T entity)  {
        Example<T> e = Example.of(entity);
        return dao.count(e);
    }

    @Override
    public void saveAndupdate(T entity)  {
        dao.save(entity);
    }

    @Override
    public void saveAndupdateAll(List<T> listentity)  {
        dao.saveAll(listentity);
    }

    @Override
    public void delete(T entity)  {
        dao.delete(entity);
    }

    @Override
    public void deleteAll(List<T> listentity)  {
        dao.deleteAll(listentity);
    }

   

}
