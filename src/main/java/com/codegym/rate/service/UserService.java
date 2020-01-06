package com.codegym.rate.service;

import com.codegym.rate.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends GeneralService<User>, UserDetailsService {
    User findByUsername(String username);

    User getCurrentUser();

    UserDetails loadUserById(Long id);

    boolean checkLogin(User user);

    boolean isRegister(User user);

    boolean isCorrectConfirmPassword(User user);

    List<User> findAllByNameContaining(String name);
}
