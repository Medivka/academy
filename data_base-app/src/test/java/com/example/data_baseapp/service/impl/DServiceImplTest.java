package com.example.data_baseapp.service.impl;

import com.example.data_baseapp.dao.springdata.DDao;
import com.example.data_baseapp.domain.model.D;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


/**
 * @author Sacuta V.A.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class DServiceImplTest {

    private static final Integer TEST_ID = 1;
    private static final String IRON_MAN = "iron_man";
    private static final String HULK = "hulk";
    private static final String IS_DEAD = "no";

    @Mock
    private DDao dDao;

    @InjectMocks
    private DServiceImpl service;

    @Test
    void when_Save_B_return_D() {
        D givenD = D.builder().hero(IRON_MAN).build();
        D askedD = D.builder().hero(HULK).build();
        Mockito.when(dDao.save(givenD)).thenReturn(askedD);
        D actual = service.save(givenD);
        D expected = service.save(givenD);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void positive_Delete_D_by_entity() {
        D d = getD();
        doNothing().when(dDao).delete(d);
    }

    @Test
    void negative_Delete_D_by_entity() {
        Mockito.doThrow(RuntimeException.class).when(dDao).delete(new D());
        Assert.assertThrows(RuntimeException.class, () -> service.delete(new D()));
    }

    @Test
    void should_getAll_D() {
        when(service.getAll()).thenReturn(List.of(new D(), new D()));
        Assertions.assertEquals(2, service.getAll().size());
    }

    @Test
    void should_getById_D() {
        D d = getD();
        when(service.getById(TEST_ID)).thenReturn(d);
        D foundD = service.getById(TEST_ID);
        assertThat(foundD).isEqualTo(d);
        verify(dDao, times(1)).getById(TEST_ID);
    }

    @Test
    void should_update_D() {
        D d = getD();
        when(dDao.findById(TEST_ID)).thenReturn(Optional.of(d));
        when(dDao.save(d)).thenReturn(d);
        d.setHero(HULK);
        D dExpected = service.save(d);
        assertThat(dExpected).isEqualTo(d);
    }

    private D getD() {
        return D.builder().hero(IRON_MAN).build();
    }
}