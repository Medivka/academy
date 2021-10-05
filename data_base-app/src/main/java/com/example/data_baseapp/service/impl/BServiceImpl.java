package com.example.data_baseapp.service.impl;

import com.example.data_baseapp.dao.entitymanager.BDaoEntity;
import com.example.data_baseapp.dao.springdata.BDao;
import com.example.data_baseapp.domain.model.B;
import com.example.data_baseapp.exceptions.MyException;
import com.example.data_baseapp.service.BService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sacuta V.A.
 */

@Service
@Transactional(rollbackFor = MyException.class)
public class BServiceImpl implements BService {

    private final BDaoEntity daoEntity;
    private final BDao bDao;

    public BServiceImpl(BDaoEntity daoEntity, BDao bDao) {
        this.daoEntity = daoEntity;
        this.bDao = bDao;
    }

    @Override
    public Boolean exist(B b) {
        return bDao.existsById(b.getId());
    }

    @Override
    public B save(B b) {
//        daoEntity.save(b);
        return bDao.save(b);
    }

    @Override
    public void delete(B b) {
        bDao.delete(b);
    }

    @Override
    public B getById(Integer id) {
//        return daoEntity.findById(id);
        return bDao.getById(id);
    }

    @Override
    public void update(B b) {
//        daoEntity.update(b);
        bDao.save(b);
    }

    @Override
    public B findById(Integer id) {
//        return daoEntity.findById(id);
        return bDao.getById(id);
    }

    @Override
    public List<B> getAll() {
        return bDao.findAll();
    }
}


