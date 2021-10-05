package com.example.data_baseapp.service.impl;

import com.example.data_baseapp.dao.springdata.ADao;
import com.example.data_baseapp.domain.model.A;
import com.example.data_baseapp.service.AService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class AServiceImplTest {

    @Autowired
    private  AService aService;
    @MockBean
    private ADao aDao;


//    @BeforeEach
//    public void init() {
//        a = new A("name");
//    }

    @Test
    public void saveA() {
               String givenMessage = "";
        A givenA = A.builder().build();
        A givenComment = A.builder().enable("on").build();
        A asvedComment = A.builder().enable("on").build();

        Mockito.when(aDao.save(givenComment)).thenReturn(asvedComment);
        Integer actual= aService.save(givenA).getId();

        Integer expected =aService.getById(actual).getId() ;
        Assertions.assertEquals(expected, actual);
        Mockito.verify(aDao, Mockito.times(1)).save(asvedComment);
        //Mockito.verifyNoMoreInteractions();
    }

//    @Test
//    public void getA() {
//        when(aService.getAll()).thenReturn(List.of(new A("first"), new A("second")));
//        assertEquals(2, aService.getAll().size());
//    }

//    @Test
//    public void deleteA() {
//        aService.delete(a);
//        verify(aDao, times(1)).delete(a);
//    }
//
//    @Test
//    public void updateA() {
//        aService.update(a);
//        verify(aDao, times(1)).save(a);
//    }
}