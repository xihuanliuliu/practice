package com.jd.edi.mock;

import com.jd.edi.common.R;
import com.jd.edi.controller.AddressBookController;
import com.jd.edi.entity.AddressBook;
import com.jd.edi.service.AddressBookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockTestController {


    @InjectMocks
    private AddressBookController addressBookController;

    @Mock
    private AddressBookService addressBookService;
    
    @Before
    public void before() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void test1() {
        Long id = 1L;
        AddressBook addressBook = new AddressBook();
        addressBook.setUserId(1L);
        addressBook.setCityCode("code");
        addressBook.setCityName("beijing");
        Mockito.when(addressBookService.getAddressBookById(id)).thenReturn(addressBook);

        R r = addressBookController.get(id);
        System.out.println("data: " +r.getData().toString());

    }


}
