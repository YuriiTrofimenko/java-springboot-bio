/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.springboot.hw.bio.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.tyaa.springboot.hw.bio.entities.Role;
import org.tyaa.springboot.hw.bio.entities.User;
import org.tyaa.springboot.hw.bio.model.JsonHttpResponse;
import org.tyaa.springboot.hw.bio.model.RoleModel;
import org.tyaa.springboot.hw.bio.model.UserModel;
import org.tyaa.springboot.hw.bio.repositories.RoleRepository;
import org.tyaa.springboot.hw.bio.repositories.UserRepository;

/**
 *
 * @author student
 */
@Service
public class AuthService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public JsonHttpResponse<List<RoleModel>> getRoles() {

        List<Role> roles = (List) roleRepository.findAll();
        List<RoleModel> roleModels
                = roles.stream()
                        .map((r) -> {
                            return new RoleModel(r.getId(), r.getName());
                        })
                        .collect(Collectors.toList());
        return new JsonHttpResponse(
                JsonHttpResponse.fetchedStatus,
                "The roles list fetched successfully",
                roleModels
        );
    }

    public JsonHttpResponse<List<UserModel>> readUser() {

        List<User> users = (List<User>) userRepository.findAll();
        List<UserModel> userModels
                = users.stream()
                        .map((u) -> {
                            return new UserModel(
                                    u.getId(),
                                    u.getName(),
                                    u.getPassword(),
                                    new RoleModel(
                                            u.getRole().getId(),
                                            u.getRole().getName()
                                    )
                            );
                        })
                        .collect(Collectors.toList());
        return new JsonHttpResponse(
                JsonHttpResponse.fetchedStatus,
                "The users list fetched successfully",
                userModels
        );
    }

    public JsonHttpResponse<UserModel> readUser(Long _id) {

        Optional<User> userOptional = userRepository.findById(_id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            UserModel userModel =
                new UserModel(
                    user.getId(),
                    user.getName(),
                    user.getPassword(),
                    new RoleModel(
                            user.getRole().getId(),
                            user.getRole().getName()
                    )
                );
            return new JsonHttpResponse(
                    JsonHttpResponse.fetchedStatus,
                    "The user fetched successfully",
                    userModel
            );
        }

        return new JsonHttpResponse(
                JsonHttpResponse.failStatus,
                "User not found",
                null
        );
    }

    public JsonHttpResponse<UserModel> readUserByName(String _name) {

        User user = userRepository.findByName(_name);
        if (user != null) {
            UserModel userModel =
                    new UserModel(
                            user.getId(),
                            user.getName(),
                            user.getPassword(),
                            new RoleModel(
                                    user.getRole().getId(),
                                    user.getRole().getName()
                            )
                    );
            return new JsonHttpResponse(
                    JsonHttpResponse.fetchedStatus,
                    "The user fetched successfully",
                    userModel
            );
        } else {
            return new JsonHttpResponse(
                    JsonHttpResponse.failStatus,
                    "User not found",
                    null);
        }
    }

    public JsonHttpResponse<UserModel> createUser(UserModel _userModel) {

        User newUser =
            userRepository.save(new User(
                _userModel.name,
                _userModel.password,
                roleRepository.findByName("user")
        ));
        return new JsonHttpResponse(
                JsonHttpResponse.createdStatus,
                "User created successfully",
                new UserModel(
                        newUser.getId(),
                        newUser.getName(),
                        newUser.getPassword(),
                        new RoleModel(
                            newUser.getRole().getId(),
                            newUser.getRole().getName()
                        )
                )
        );
    }

    public JsonHttpResponse check(Authentication authentication) {

        JsonHttpResponse response = new JsonHttpResponse();
        if (authentication != null && authentication.isAuthenticated()) {
            UserModel userModel = new UserModel();
            userModel.name = authentication.getName();
            response.status = JsonHttpResponse.successStatus;
            response.message
                    = String.format("User %s signed in", userModel.name);
            response.data = userModel;
        } else {
            response.status = JsonHttpResponse.failStatus;
            response.message = "User is a guest";
        }
        return response;
    }

    public JsonHttpResponse onSignOut() {
        JsonHttpResponse response
                = new JsonHttpResponse();
        response.status = JsonHttpResponse.successStatus;
        response.message = "Signed out";
        return response;
    }

    public JsonHttpResponse onError() {
        JsonHttpResponse response
                = new JsonHttpResponse();
        response.status = JsonHttpResponse.errorStatus;
        response.message = "Auth error";
        return response;
    }
}
