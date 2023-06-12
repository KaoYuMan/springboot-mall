package com.milly.springbootmall.service;

import com.milly.springbootmall.dto.UserLoginRequest;
import com.milly.springbootmall.dto.UserRegisterRequest;
import com.milly.springbootmall.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);
}
