package com.example.data_baseapp.service.impl;

import com.example.data_baseapp.dao.entitymanager.CdaoEntity;
import com.example.data_baseapp.dao.springdata.CDao;
import com.example.data_baseapp.domain.model.C;
import com.example.data_baseapp.exceptions.MyException;
import com.example.data_baseapp.service.CService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sacuta V.A.
 */

@Service
@Transactional(rollbackFor = MyException.class)
public class CServiceImpl implements CService {

    private final CDao cDao;
    private final CdaoEntity cdaoEntity;

    public CServiceImpl(CDao cDao, CdaoEntity cdaoEntity) {
        this.cDao = cDao;
        this.cdaoEntity = cdaoEntity;
    }

    @Override
    public C save(C c) {
//        cdaoEntity.save(c);
        return cDao.save(c);
    }

    @Override
    public void delete(C c) {
//        cdaoEntity.delete(c);
        cDao.delete(c);
    }

    @Override
    public C getById(Integer id) {
//        return cdaoEntity.findById(id);
        return cDao.getById(id);
    }

    @Override
    public C update(C c) {
//        cdaoEntity.update(c);
       return cDao.save(c);
    }

    @Override
    public C findById(Integer id) {
//        return cdaoEntity.findById(id);
        return cDao.getById(id);
    }

    @Override
    public List<C> getAll() {
        return cDao.findAll();
    }

    @Override
    public Boolean exist(C c) {
        return cDao.existsById(c.getId());
    }

}
