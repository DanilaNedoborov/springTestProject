package com.example.demoforlesson.service.impl;

import com.example.demoforlesson.service.StringService;
import org.springframework.stereotype.Service;

@Service
public class StringServiceImpl implements StringService {

    @Override
    public String produceName(String name) {
        return "Hello is service " + name;
    }
}
