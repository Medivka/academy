package com.example.data_baseapp.service.impl;

import com.example.data_baseapp.dao.springdata.CDao;
import com.example.data_baseapp.domain.model.C;
import com.example.data_baseapp.service.CService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CServiceImplTest {
    @Autowired
    private CService cService;

    @MockBean
    private CDao cDao;

    private C c;

    @BeforeEach
    public void init() {
        c = new C();
    }

    @Test
    public void saveC() {when(cDao.save(c)).thenReturn(c);
    }


    @Test
    public void deleteC() { cService.delete(c);
        verify(cDao, times(1)).delete(c);
    }

    @Test
    public void getC() {
        when(cService.getAll()).thenReturn(List.of(new C(), new C()));
        assertEquals(2, cService.getAll().size());
    }

    @Test
    public void updateC() {
        cService.update(c);
        verify(cDao, times(1)).save(c);
    }
}