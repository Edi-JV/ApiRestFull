package com.m5b.ApiRestFull.app.service;

import com.m5b.ApiRestFull.app.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> findById(Long id);

    void deleteById(Long id);

    public User save(User user);

    public Iterable<User> findAll();

}
