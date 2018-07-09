package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Create by lifan.
 * Date: 2018/7/6.
 * Time: 15:57.
 */
@RestController
@Slf4j
public class LoginController {

    @PostMapping({"/login/valid"})
    public ResponseEntity login(Model model, String username, String password,
                                           String validateCode, String remember, HttpSession httpSession){
        log.info("username={},passowrd={},validateCode={}",username,password,validateCode);
        return null;
    }


}
