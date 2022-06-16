package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.AccountForm;
import com.example.demo.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {

	private final AccountService accountService = null;

    @GetMapping("/loginUser")
    public String createUserForm(Model model){
        model.addAttribute("userForm",new AccountForm());
        return "user/login/register";
    }

    @PostMapping("/loginUser")
    public String createUser(@Valid AccountForm form, BindingResult result){
		if (result.hasErrors()) {
            return "user/login/register";
        }
        accountService.createUser(form);

        return "redirect:/";
    }
}
