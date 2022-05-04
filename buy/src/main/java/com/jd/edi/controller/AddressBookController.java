package com.jd.edi.controller;


import com.jd.edi.auth.AuthContext;
import com.jd.edi.common.R;
import com.jd.edi.entity.AddressBook;
import com.jd.edi.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 地址簿管理
 */
@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增
     */
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook) {
        addressBook.setUserId(AuthContext.currentUser());
        log.info("addressBook:{}", addressBook);
        addressBookService.saveAddressBook(addressBook);
        return R.success(addressBook);
    }

    /**
     * 更新
     */
    @PutMapping
    public R<AddressBook> update(@RequestBody AddressBook addressBook) {
        addressBookService.updateAddressBook(addressBook);
        return R.success(addressBook);
    }

    /**
     * 设置默认地址
     */
    @PutMapping("default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook) {
        log.info("addressBook:{}", addressBook);
        addressBookService.updateDefaultAddressBook(addressBook);
        return R.success(addressBook);
    }

    /**
     * 根据id查询地址
     */
    @GetMapping("/{id}")
    public R get(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getAddressBookById(id);
        if (addressBook != null) {
            return R.success(addressBook);
        } else {
            return R.error("没有找到该对象");
        }
    }

    /**
     * 查询默认地址
     */
    @GetMapping("default")
    public R<AddressBook> getDefault() {
        AddressBook addressBook = addressBookService.getDefaultAddressBookById();
        if (null == addressBook) {
            return R.error("没有找到该对象");
        } else {
            return R.success(addressBook);
        }
    }

    /**
     * 查询指定用户的全部地址
     */
    @GetMapping("/list")
    public R<List<AddressBook>> list() {
        List<AddressBook> addressBooks = addressBookService.getAddressBooks(AuthContext.currentUser());
        return R.success(addressBooks);
    }
}
