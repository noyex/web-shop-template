package com.noyex.userservice.service;

import com.noyex.userservice.entity.DTO.UserDTO;
import com.noyex.userservice.entity.User;

public interface IUserService {
    User createUser(UserDTO userDTO);
    User getUserById(Long id);
}
