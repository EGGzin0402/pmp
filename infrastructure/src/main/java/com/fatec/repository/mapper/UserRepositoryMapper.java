package com.fatec.repository.mapper;

import com.fatec.entity.User;
import com.fatec.entity.enumerables.UserRole;
import com.fatec.persistence.UserPersistence;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryMapper {

    private UserRepositoryMapper() {
    }

    public static User toEntity(UserPersistence persistence) {
        List<UserRole> roles = Arrays.stream(persistence.getRoles().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(UserRole::valueOf)
                .toList();
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(persistence, User.class);
        user.setRoles(roles);
        return user;
    }

    public static UserPersistence toPersistence(User entity) {
        String roles = entity.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));
        ModelMapper modelMapper = new ModelMapper();
        UserPersistence userPersistence = modelMapper.map(entity, UserPersistence.class);
        userPersistence.setRoles(roles);
        return userPersistence;
    }

}
