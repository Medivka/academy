package com.example.data_baseapp.service.impl;

import com.example.data_baseapp.dao.springdata.CDao;
import com.example.data_baseapp.domain.model.C;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Sacuta V.A.
 */


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CServiceImplTest {

    private static final Integer TEST_ID = 1;
    private static final String NAME_FIRST = "name_1";
    private static final String NAME_SECOND = "name_2";

    @Mock
    private CDao cDao;

    @InjectMocks
    private CServiceImpl service;

    @Test
    void when_Save_C_return_C() {

        C givenC = C.builder().name(NAME_FIRST).build();
        C askedC = C.builder().name(NAME_SECOND).build();
        Mockito.when(cDao.save(givenC)).thenReturn(askedC);
        C actual = service.save(givenC);
        C expected = service.save(givenC);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void positive_Delete_C_by_entity() {
        C c = getC();
        doNothing().when(cDao).delete(c);
    }

    @Test
    void negative_Delete_C_by_entity() {
        Mockito.doThrow(RuntimeException.class).when(cDao).delete(new C());
        Assert.assertThrows(RuntimeException.class, () -> service.delete(new C()));
    }

    @Test
    void should_getAll_C() {
        when(service.getAll()).thenReturn(List.of(new C(), new C()));
        Assertions.assertEquals(2, service.getAll().size());
    }

    @Test
    void should_getById_C() {
        C c = getC();
        when(service.getById(TEST_ID)).thenReturn(c);
        C foundC = service.getById(TEST_ID);
        assertThat(foundC).isEqualTo(c);
        verify(cDao, times(1)).getById(TEST_ID);
    }

    @Test
    void should_update_C() {
        C c = getC();
        when(cDao.findById(TEST_ID)).thenReturn(Optional.of(c));
        when(cDao.save(c)).thenReturn(c);
        c.setName(NAME_SECOND);
        C CExpected = service.save(c);
        assertThat(CExpected).isEqualTo(c);
    }

    private C getC() {
        return C.builder().name(NAME_FIRST).build();
    }
}