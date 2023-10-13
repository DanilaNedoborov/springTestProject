package com.example.groupchat.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ChatController {

    @GetMapping("/init")
    public Boolean init(){
        //TODO: check sessionId. If found => true, if not => false
        return true;
    }

    @PostMapping("/message")
    public Boolean sendMessage(@RequestParam String message){
        return true;
    }

    @GetMapping("/meassage")
    public List<String> getMessagesList(){
        return null;
    }

    @GetMapping("/users")
    public HashMap<Integer, String> getUserList(){
        return null;
    }
}
