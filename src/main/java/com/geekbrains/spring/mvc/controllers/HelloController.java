package com.geekbrains.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("message", "new world!");
        //WEB-INF/templates/hello.html
        return "hello";
    }

    @RequestMapping(value = "/redirectToIndex", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:/";
    }

}
