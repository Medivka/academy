package com.example.data_baseapp.service.impl;

import com.example.data_baseapp.dao.springdata.ADao;
import com.example.data_baseapp.domain.model.A;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Sacuta V.A.
 */


@ExtendWith(MockitoExtension.class)
public class AServiceImplTest {

    private static final Integer TEST_ID = 1;
    private static final String NAME_FIRST = "name_1";
    private static final String NAME_SECOND = "name_2";

    @Mock
    private ADao aDao;

    @InjectMocks
    private AServiceImpl service;

    @Test
    void when_Save_A_return_A() {
        A givenA = A.builder().name(NAME_FIRST).build();
        A askedA = A.builder().name(NAME_SECOND).build();
        Mockito.when(aDao.save(givenA)).thenReturn(askedA);
        A actual = service.save(givenA);
        A expected = service.save(givenA);
        assertEquals(actual, expected);
    }

    @Test
    void positive_Delete_A_by_entity() {
        A a = getA();
        lenient().doNothing().when(aDao).delete(a);
    }

    @Test
    void negative_Delete_A_by_entity() {
        Mockito.doThrow(RuntimeException.class).when(aDao).delete(new A());
        Assert.assertThrows(RuntimeException.class, () -> service.delete(new A()));
    }

    @Test
    void should_getAll_A() {
        when(service.getAll()).thenReturn(List.of(new A(), new A()));
        assertEquals(2, service.getAll().size());
    }

    @Test
    void should_getById_A() {
        A a = getA();
        when(service.getById(TEST_ID)).thenReturn(a);
        A foundA = service.getById(TEST_ID);
        assertThat(foundA).isEqualTo(a);
        verify(aDao, times(1)).getById(TEST_ID);
    }

    @Test
    void should_update_A() {
        A a = getA();
        when(aDao.save(a)).thenReturn(a);
        a.setName(NAME_SECOND);
        A AExpected = service.save(a);
        assertThat(AExpected).isEqualTo(a);
    }

    private A getA() {
        return A.builder().name(NAME_FIRST).build();
    }
}