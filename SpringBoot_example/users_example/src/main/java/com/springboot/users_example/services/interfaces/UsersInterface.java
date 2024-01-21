package com.springboot.users_example.services.interfaces;

import java.util.List;

import com.springboot.users_example.model.transferObject.UsersDTO;

public interface UsersInterface {

    List<UsersDTO> getAllUsers();

}
