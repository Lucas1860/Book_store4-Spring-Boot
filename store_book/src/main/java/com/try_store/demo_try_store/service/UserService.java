package com.try_store.demo_try_store.service;

import com.try_store.demo_try_store.dto.RegistrationDto;
import com.try_store.demo_try_store.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
