package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	final String logPrefix = "home controller";

	@GetMapping("/")
    public String home(Model model){
        log.info(logPrefix);
        return "home";
    }
    @GetMapping("/user")
    public String dispUser(Model model){
    	log.info(logPrefix);
        return "/user/user";
    }
    @GetMapping("/manager")
    public String dispManager(Model model){
    	log.info(logPrefix);
        return "/user/manager";
    }
    @GetMapping("/admin")
    public String dispAdmin(Model model){
    	log.info(logPrefix);
        return "/user/admin";
    }
}
