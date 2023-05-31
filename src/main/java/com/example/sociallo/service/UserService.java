package com.example.sociallo.service;

import com.example.sociallo.model.User;
import com.example.sociallo.payloads.request.UpdateUserReq;
import jdk.jshell.spi.ExecutionControl;
import jdk.jshell.spi.ExecutionControl.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User findUserById(Long id);

    public User findUserProfile(String jwt);

    public User updateUser(Long userId, UpdateUserReq updateUserReq) throws UserException;

    public List<User> searchUser(String query);


}
