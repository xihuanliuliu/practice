package com.jd.edi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jd.edi.auth.AuthContext;
import com.jd.edi.entity.AddressBook;
import com.jd.edi.mapper.AddressBookMapper;
import com.jd.edi.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class AddressBookServiceImpl implements AddressBookService {

    @Resource
    private AddressBookMapper addressBookMapper;

    @Transactional
    @Override
    public void saveAddressBook(AddressBook addressBook) {
        addressBookMapper.insert(addressBook);
    }

    @Transactional
    @Override
    public void updateAddressBook(AddressBook addressBook) {
        addressBookMapper.updateById(addressBook);
    }

    @Transactional
    @Override
    public void updateDefaultAddressBook(AddressBook addressBook) {
        // 1.先将所有的设置为非默认
        LambdaUpdateWrapper<AddressBook> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(AddressBook::getUserId, AuthContext.currentUser());
        updateWrapper.set(AddressBook::getIsDefault, 0);
        addressBookMapper.update(null, updateWrapper);

        // 2.再设置默认地址
        addressBook.setIsDefault(1);
        addressBookMapper.updateById(addressBook);
    }

    @Override
    public AddressBook getAddressBookById(Long id) {
        return addressBookMapper.selectById(id);
    }

    @Override
    public AddressBook getDefaultAddressBookById() {
        Long userId = AuthContext.currentUser();
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId, userId);
        queryWrapper.eq(AddressBook::getIsDefault, 1);
        return addressBookMapper.selectOne(queryWrapper);
    }

    @Override
    public List<AddressBook> getAddressBooks(Long userId) {
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId, userId);
        return addressBookMapper.selectList(queryWrapper);
    }
}
