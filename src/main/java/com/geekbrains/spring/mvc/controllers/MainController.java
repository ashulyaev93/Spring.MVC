package com.geekbrains.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    // root (context path) = http://localhost:8189/app
    // GET http://localhost:8989/app/hello?name=Alex
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(
            @RequestParam(required = false) String name
    ) {
        if (name == null) {
            return "Hello, user!!!";
        }
        return String.format("Hello, %s!!!", name);
    }

    // GET http://localhost:8989/app/add?n1=100&n2=500
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public Integer addTwoNumbers(
            @RequestParam(name = "n1") Integer value1,
            @RequestParam(name = "n2") Integer value2
    ) {
        return value1 + value2;
    }

    // GET http://localhost:8189/app/users/info/{id = 2}/confirm
    // http://localhost:8989/app/users/info/5/confirm
    @GetMapping("/users/info/{id}/confirm")
    @ResponseBody
    public String getUserInfo(
            @PathVariable String id
    ) {
        return "User Info #" + id;
    }

}
