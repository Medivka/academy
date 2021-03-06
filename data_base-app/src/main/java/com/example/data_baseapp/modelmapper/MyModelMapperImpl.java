package com.example.data_baseapp.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Sacuta V.A.
 */

@Component
public class MyModelMapperImpl implements MyModelMapper {

    private final ModelMapper mapper;

    public MyModelMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <A, B> B map(A src, Class<B> clazz) {
        if (src != null) {
            return mapper.map(src, clazz);
        }
        return null;
    }

    @Override
    public <A, B> List<B> mapCollections(Collection<A> src, Class<B> clazz) {
        if (src == null || src.size() == 0) {
            return new ArrayList<B>(0);
        }
        List<B> dst = new ArrayList<B>();
        for (A a : src) {
            if (a != null) {
                dst.add(mapper.map(a, clazz));
            }
        }
        return dst;
    }

    @Override
    public <A, B> void map(A src, B target) {
        if (src != null) {
            mapper.map(src, target);
        }
    }
}
