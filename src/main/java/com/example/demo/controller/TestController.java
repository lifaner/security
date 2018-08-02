package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by lifan.
 * Date: 2018/7/31.
 * Time: 13:54.
 */
@RestController
@RequestMapping({"/test"})
public class TestController {

    @GetMapping(value = "/test")
    public String testAOP() {
        System.out.println("test");
        return "test";
    }

}
