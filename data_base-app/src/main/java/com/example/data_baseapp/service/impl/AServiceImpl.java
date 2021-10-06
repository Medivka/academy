package com.example.data_baseapp.service.impl;

import com.example.data_baseapp.dao.entitymanager.ADaoEntity;
import com.example.data_baseapp.dao.springdata.ADao;
import com.example.data_baseapp.domain.model.A;
import com.example.data_baseapp.exceptions.MyException;
import com.example.data_baseapp.service.AService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sacuta V.A.
 */

@Service
@Transactional(rollbackFor = MyException.class)
public class AServiceImpl implements AService {

    private final ADao aDao;
    private final ADaoEntity aDaoEntity;

    public AServiceImpl(ADao aDao, ADaoEntity aDaoEntity) {
        this.aDao = aDao;
        this.aDaoEntity = aDaoEntity;
    }

    @Override
    public A save(A a) {
//
        return aDao.save(a);                             //Spring Data
    }

    @Override
    public void delete(A a) {
        aDao.delete(a);
    }

    @Override
    public A getById(Integer id) {
//        return aDaoEntity.findById(id);
        return aDao.getById(id);
    }

    @Override
    public A update(A a) {
//        aDaoEntity.update(a);
        return aDao.save(a);
    }

    @Override
    public A findById(Integer id) {
//        return aDaoEntity.findById(id);
        return aDao.getById(id);
    }

    @Override
    public List<A> getAll() {
        return aDao.findAll();
    }

    @Override
    public Boolean exist(A a) {
        return aDao.existsById(a.getId());
    }

    @Override
    public A findByName(String name) {
        return aDao.findMyByName(name);
    }

    ;
}
