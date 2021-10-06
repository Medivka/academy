package com.example.data_baseapp.service.impl;

import com.example.data_baseapp.dao.springdata.BDao;
import com.example.data_baseapp.domain.model.B;
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
class BServiceImplTest {

    private static final Integer TEST_ID = 1;
    private static final String ADDRESS_FIRST = "address_1";
    private static final String ADDRESS_SECOND = "address_2";
    private static final String IS_DEAD = "no";

    @Mock
    private BDao bDao;

    @InjectMocks
    private BServiceImpl bService;

    @Test
    void when_Save_B_return_B() {
        B givenB = B.builder().address(ADDRESS_FIRST).build();
        B askedB = B.builder().address(ADDRESS_SECOND).build();
        Mockito.when(bDao.save(givenB)).thenReturn(askedB);
        B actual = bService.save(givenB);
        B expected = bService.save(givenB);
        assertEquals(actual, expected);
    }

    @Test
    void positive_Delete_B_by_entity() {
        B b = getB();
        lenient().doNothing().when(bDao).delete(b);
    }

    @Test
    void negative_Delete_B_by_entity() {
        Mockito.doThrow(RuntimeException.class).when(bDao).delete(new B());
        Assert.assertThrows(RuntimeException.class, () -> bService.delete(new B()));
    }

    @Test
    void should_getAll_B() {
        when(bService.getAll()).thenReturn(List.of(new B(), new B()));
        assertEquals(2, bService.getAll().size());
    }

    @Test
    void should_getById_B() {
        B b = getB();
        when(bService.getById(TEST_ID)).thenReturn(b);
        B foundB = bService.getById(TEST_ID);
        assertThat(foundB).isEqualTo(b);
        verify(bDao, times(1)).getById(TEST_ID);
    }

    @Test
    void should_update_B() {
        B b = getB();
        when(bDao.save(b)).thenReturn(b);
        b.setAddress(ADDRESS_SECOND);
        B bExpected = bService.update(b);
        assertThat(bExpected).isEqualTo(b);
    }

    private B getB() {
        return B.builder().address(ADDRESS_FIRST).isDead(IS_DEAD).build();
    }
}