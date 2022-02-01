package com.m5b.ApiRestFull.app.repository;

import com.m5b.ApiRestFull.app.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
