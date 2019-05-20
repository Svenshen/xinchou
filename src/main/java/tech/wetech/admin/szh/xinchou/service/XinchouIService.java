/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.xinchou.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.wetech.admin.modules.base.query.PageQuery;

/**
 *
 * @author  szh
 * QQ:873689
 * @param <T>
 * @date 2019-5-9 15:19:42
 */
public interface XinchouIService<T,ID> {
    
    List<T> queryAll();

    List<T> queryList(T entity);

    T queryOne(T entity);

    T queryById(ID id);

    Page<T> queryList(T entity,  Pageable pageable);

    void saveAndupdate(T entity) ;
    
    void saveAndupdateAll(List<T> listentity) ;
    
    void deleteById(ID id) ;
    
    void delete(T entity) ;
    
    void deleteAll(List<T> listentity) ;

    long count(T entity) ;

}
