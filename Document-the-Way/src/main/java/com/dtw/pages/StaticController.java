package com.dtw.pages;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class StaticController {


    @GetMapping("login")
    public  String getLoginPage ( ){
          return "login";
    }
}
