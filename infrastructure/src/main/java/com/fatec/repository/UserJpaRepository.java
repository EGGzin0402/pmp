package com.fatec.repository;

import com.fatec.persistence.UserPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJpaRepository extends JpaRepository<UserPersistence, Long> {
    UserPersistence findByUsername(String username);

    @Query("SELECT u.roles FROM UserPersistence u WHERE u.username like :username")
    String findRolesByUsername(String username);
}