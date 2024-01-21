package com.springboot.users_example.model.transferObject.mapper;

import com.springboot.users_example.model.UserEntity;
import com.springboot.users_example.model.transferObject.UsersDTO;

public class UsersMapper {

    public static UsersDTO convertToDTO(UserEntity UserEntity) {

        UsersDTO UsersDTO = new UsersDTO();

        UsersDTO.setUserId(UserEntity.getUserId());
        UsersDTO.setUsername(UserEntity.getUsername());
        UsersDTO.setFirstName(UserEntity.getFirstName());
        UsersDTO.setLastName(UserEntity.getLastName());
        UsersDTO.setGender(UserEntity.getGender());
        UsersDTO.setPassword(UserEntity.getPassword());
        UsersDTO.setStatus(UserEntity.getStatus());

        return UsersDTO;
    }

}
