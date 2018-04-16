package com.example.xy_restaurant.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HellowController {

    @RequestMapping("/hellow")
    public String hellow(){
        return "hellow";
    }
}
