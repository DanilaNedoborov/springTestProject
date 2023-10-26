package com.example.demoforlesson.controller;

import com.example.demoforlesson.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("firstController")
public class FirstController {

    @Autowired
    private StringService service;

    @GetMapping("/getOk/{name}")
    public String getSimpleString(@PathVariable String name){
        return service.produceName(name);
    }
}
