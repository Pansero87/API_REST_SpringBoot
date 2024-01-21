package com.springboot.users_example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.users_example.model.UserEntity;
import com.springboot.users_example.model.transferObject.UsersDTO;
import com.springboot.users_example.model.transferObject.mapper.UsersMapper;
import com.springboot.users_example.repositories.UsersRepository;
import com.springboot.users_example.services.interfaces.UsersInterface;

@Service
public class UsersServiceImpl implements UsersInterface {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UsersDTO> getAllUsers() {
        List<UserEntity> users = usersRepository.findAll();
        List<UsersDTO> usersDTO = new ArrayList<>();
        for (UserEntity user : users) {
            UsersDTO dto = UsersMapper.convertToDTO(user);
            usersDTO.add(dto);
        }
        return usersDTO;
    }

}
