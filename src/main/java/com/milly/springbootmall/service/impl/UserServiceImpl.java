package com.milly.springbootmall.service.impl;

import com.milly.springbootmall.dao.UserDao;
import com.milly.springbootmall.dto.UserRegisterRequest;
import com.milly.springbootmall.model.User;
import com.milly.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
