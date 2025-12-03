package com.fatec.repository;

import com.fatec.entity.User;
import com.fatec.persistence.UserPersistence;
import com.fatec.repository.mapper.UserRepositoryMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final UserJpaRepository jpaRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRepositoryImpl(UserJpaRepository jpaRepository, PasswordEncoder passwordEncoder) {
        this.jpaRepository = jpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserPersistence userPersistence = UserRepositoryMapper.toPersistence(user);
        return UserRepositoryMapper.toEntity(jpaRepository.save(userPersistence));
    }

    @Override
    public User findByUsername(String username) {
        return UserRepositoryMapper.toEntity(jpaRepository.findByUsername(username));
    }

    @Override
    public String findRolesByUsername(String username) {
        return jpaRepository.findRolesByUsername(username);
    }
}
