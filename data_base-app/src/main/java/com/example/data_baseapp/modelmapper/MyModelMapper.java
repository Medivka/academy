package com.example.data_baseapp.modelmapper;

import java.util.Collection;
import java.util.List;

/**
 * @author Sacuta V.A.
 */


public interface MyModelMapper {
    <A, B> B map(A src, Class<B> clazz);

    <A, B> List<B> mapCollections(Collection<A> src, Class<B> clazz);

    <A, B> void map(A src, B target);
}
