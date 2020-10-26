package com.example.demo1.service.user_service;

import com.example.demo1.model.AppUser;

public interface AppUserService {
    AppUser getUserByUsername(String username);
}
