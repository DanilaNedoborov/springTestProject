package com.example.groupchat.services;

import java.util.HashMap;

public interface IUserService {
    HashMap<String, Boolean> auth(String name);
    HashMap<String, Boolean> init();
}
