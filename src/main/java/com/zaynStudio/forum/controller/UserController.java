package com.zaynStudio.forum.controller;

import com.zaynStudio.forum.util.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.zaynStudio.forum.util.ResponseUtil;
import com.zaynStudio.forum.model.vo.UserVO;
import com.zaynStudio.forum.service.UserService;

import java.math.BigDecimal;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseUtil getUserProfile(@RequestParam String userKey) {
        try {
            return ResponseUtil.response(userService.getUserProfile(userKey));
        } catch (Exception e) {
            return returnError();
        }
    }

    @PostMapping("/login")
    public ResponseUtil userLogin(@RequestParam String userKey, @RequestParam String userPSW) {
        try {
            return ResponseUtil.response(userService.userLogin(userKey, userPSW));
        } catch (Exception e) {
            return returnError();
        }
    }

    @GetMapping("/register")
    public ResponseUtil userRegister(@RequestParam UserVO userVO, @RequestParam String userPSW) {
        try {
            return ResponseUtil.response(userService.userRegister(userVO, userPSW));
        } catch (Exception e) {
            return returnError();
        }
    }

    @GetMapping("/testHello")
    public ResponseUtil testHello() {
        return ResponseUtil.response("HELLO!!!");
    }

    @GetMapping("/testError")
    public ResponseUtil testError() {
        return ResponseUtil.response(new ErrorUtil("11111"," Fetal error..."));
    }

    private ResponseUtil returnError() {
        return ResponseUtil.response(new ErrorUtil("11111","Something bad happened...sorry im fixing QAQ"));
    }

}
