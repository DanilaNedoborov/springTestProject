package com.example.groupchat.controller;

import com.example.groupchat.dto.DtoMessage;
import com.example.groupchat.dto.MessageMapper;
import com.example.groupchat.model.Massage;
import com.example.groupchat.model.MassageRepo;
import com.example.groupchat.model.User;
import com.example.groupchat.model.UserRepo;
import com.example.groupchat.services.IUserService;
import com.example.groupchat.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class ChatController {
    @Autowired
    private MassageRepo massageRepo;
    @Autowired
    private UserRepo userRepo;

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
    public Map<String, Boolean> sendMessage(@RequestParam String message){
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = userRepo.findBySessionId(sessionId).get();
        if (Strings.isEmpty(message)){
            return Map.of("result", false);
        }
        Massage massage = new Massage();
        massage.setMassage(message);
        massage.setUser(user);
        massage.setDateTime(LocalDateTime.now());
        massageRepo.save(massage);
        return Map.of("result", true);
    }

    @GetMapping("/message")
    public List<DtoMessage> getMessagesList(){
        return massageRepo.findAll(Sort.by(Sort.Direction.ASC, "dateTime")).stream()
                .map(massage -> MessageMapper.map(massage))
                .collect(Collectors.toList());
    }

    @GetMapping("/users")
    public HashMap<Integer, String> getUserList(){
        HashMap<Integer, String> userList = new HashMap<>();
        //userList.put(userRepo.findAll().iterator().next().getId(), userRepo.findAll().iterator().next().getName());
        userRepo.findAll().forEach(user -> userList.put(user.getId(), user.getName()));
        return userList;
    }
}
