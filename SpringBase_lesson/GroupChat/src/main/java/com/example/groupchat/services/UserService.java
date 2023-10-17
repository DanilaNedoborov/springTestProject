package com.example.groupchat.services;

import com.example.groupchat.model.User;
import com.example.groupchat.model.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public HashMap<String, Boolean> auth(String name) {
        HashMap<String, Boolean> response = new HashMap<>();
        if (name.isEmpty()){
            response.put("result", false);
            return response;
        }
        User user = new User();
        user.setName(name);
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        user.setSessionId(sessionId);
        userRepo.save(user);
        response.put("result", true);
        return response;
    }

    @Override
    public HashMap<String, Boolean> init() {
        HashMap<String, Boolean> response = new HashMap<>();
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
//        if(userRepo.findBySessionId(sessionId).isPresent()){
//            response.put("result", true);
//            return response;
//        }
//        response.put("result", false);
//        return response;
        Optional<User> userOptional = userRepo.findBySessionId(sessionId);
        response.put("result", userOptional.isPresent());
        return response;
    }


}
