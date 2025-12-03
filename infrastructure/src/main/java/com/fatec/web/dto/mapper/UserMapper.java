package com.fatec.web.dto.mapper;

import com.fatec.entity.User;
import com.fatec.web.dto.CreateDto;
import org.modelmapper.ModelMapper;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(CreateDto dto) {
        return new ModelMapper().map(dto, User.class);
    }

}
