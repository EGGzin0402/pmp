package com.fatec.repository;

import com.fatec.entity.User;

public interface UserRepository {

    User save(User user);

    User findByUsername(String username);

    String findRolesByUsername(String username);
}
