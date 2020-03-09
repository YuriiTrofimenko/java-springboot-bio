/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.springboot.hw.bio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author student
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel {

    public Integer id;
    public String name;
    public String password;
    public RoleModel role;
    public Boolean subscribe;
    
    public UserModel() {}

    public UserModel(Integer id, String name, String password, RoleModel role, Boolean subscribe) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.subscribe = subscribe;
    }

    public UserModel(String name, String password, RoleModel role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public UserModel(Integer id, String name, String password, RoleModel role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public UserModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
