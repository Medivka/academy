package com.example.data_baseapp.service.impl;

import com.example.data_baseapp.dao.springdata.DDao;
import com.example.data_baseapp.domain.model.D;
import com.example.data_baseapp.service.DService;
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
public class DServiceImplTest {

    @Autowired
    private DService dService;

    @MockBean
    private DDao dDao;

    private D d;

    @BeforeEach
    public void init() {
        d = new D();
    }

    @Test
    public void saveD() {when(dDao.save(d)).thenReturn(d);
    }

    @Test
    public void deleteD() {
    }

    @Test
    public void getD() { when(dService.getAll()).thenReturn(List.of(new D(), new D()));
        assertEquals(2, dService.getAll().size());
    }

    @Test
    public void updateD() {
        dService.update(d);
        verify(dDao, times(1)).save(d);
    }
}