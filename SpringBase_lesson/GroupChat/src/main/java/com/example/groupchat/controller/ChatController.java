package com.example.groupchat.controller;

import com.example.groupchat.model.MassageRepo;
import com.example.groupchat.model.User;
import com.example.groupchat.model.UserRepo;
import com.example.groupchat.services.IUserService;
import com.example.groupchat.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@RestController
public class ChatController {
    private final MassageRepo massageRepo;

    private IUserService userService;

    @GetMapping("/init")
    public HashMap<String, Boolean> init(){
//        HashMap<String, Boolean> response = new HashMap<>();
//        //TODO: check sessionId. If found => true, if not => false
//        response.put("result", false);
        return userService.init();
    }

    @PostMapping("/auth")
    public HashMap<String, Boolean> auth(@RequestParam String name){
        return userService.auth(name);
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
