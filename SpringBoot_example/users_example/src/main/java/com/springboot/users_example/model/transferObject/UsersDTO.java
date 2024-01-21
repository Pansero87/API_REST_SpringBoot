package com.springboot.users_example.model.transferObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersDTO {

    private int userId;
    private String username;
    private String firstName;
    private String lastName;
    private String gender;
    private String password;
    private Integer status;

}
