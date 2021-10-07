package com.example.data_baseapp.service.impl;

import com.example.data_baseapp.dao.entitymanager.DDaoEntity;
import com.example.data_baseapp.dao.springdata.DDao;
import com.example.data_baseapp.domain.model.D;
import com.example.data_baseapp.exceptions.MyException;
import com.example.data_baseapp.service.DService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sacuta V.A.
 */

@Service
@Transactional(rollbackFor = MyException.class)
public class DServiceImpl implements DService {

    private final DDao dao;
    private final DDaoEntity daoEntity;

    public DServiceImpl(DDao dao, DDaoEntity daoEntity) {
        this.dao = dao;
        this.daoEntity = daoEntity;
    }

    @Override
    public D save(D d) {
//        daoEntity.save(d);
        return dao.save(d);
    }

    @Override
    public void delete(D d) {
        dao.delete(d);
//        daoEntity.delete(d);
    }

    @Override
    public D getById(Integer id) {
//        return daoEntity.findById(id);
        return dao.getById(id);
    }

    @Override
    public D update(D d) {
//        daoEntity.update(d);
        return dao.save(d);
    }

    @Override
    public D findById(Integer id) {
//        return daoEntity.findById(id);
        return dao.getById(id);
    }

    @Override
    public List<D> getAll() {
        return dao.findAll();
    }

    @Override
    public Boolean exist(D d) {
        return dao.existsById(d.getId());
    }
}

