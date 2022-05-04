package com.jd.edi.service;

import com.jd.edi.entity.AddressBook;

import java.util.List;

public interface AddressBookService {

    void saveAddressBook(AddressBook addressBook);

    void updateAddressBook(AddressBook addressBook);

    void updateDefaultAddressBook(AddressBook addressBook);

    AddressBook getAddressBookById(Long id);

    AddressBook getDefaultAddressBookById();

    List<AddressBook> getAddressBooks(Long userId);
}
