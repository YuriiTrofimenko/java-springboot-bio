/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.springboot.hw.bio.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.springboot.hw.bio.entities.Role;
import org.tyaa.springboot.hw.bio.model.JsonHttpResponse;
import org.tyaa.springboot.hw.bio.model.RoleModel;
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
    
    public JsonHttpResponse<List<RoleModel>> readRole() {

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

        List<User> users = userDAO.read();
        List<UserModel> userModels
                = users.stream()
                        .map((u) -> {
                            return new UserModel(
                                    u.getId(),
                                    u.getName(),
                                    u.getPassword(),
                                    u.getMail(),
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
}
