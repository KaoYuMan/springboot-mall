package com.milly.springbootmall.controller;

import com.milly.springbootmall.dto.UserLoginRequest;
import com.milly.springbootmall.dto.UserRegisterRequest;
import com.milly.springbootmall.model.User;
import com.milly.springbootmall.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "User list 相關api")
public class UserController {
    @Autowired
    private UserService userService;


    @ApiOperation("註冊新帳號")
    @ApiResponses({
            @ApiResponse(code=401,message="沒有權限"),
            @ApiResponse(code=404,message="找不到路徑") })
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
     Integer userId = userService.register(userRegisterRequest);

     User user = userService.getUserById(userId);

     return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @ApiOperation("登入")
    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody@Valid UserLoginRequest userLoginRequest){
        User user = userService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
