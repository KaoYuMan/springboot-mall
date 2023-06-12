package com.milly.springbootmall.dao;

import com.milly.springbootmall.dto.UserRegisterRequest;
import com.milly.springbootmall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);
    User getUserById(Integer userId);

    User getUserByEmail(String email);
}
