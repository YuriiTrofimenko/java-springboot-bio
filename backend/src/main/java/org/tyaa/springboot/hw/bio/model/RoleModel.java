/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.springboot.hw.bio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 *
 * @author student
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleModel {
    public Integer id;
    public String name;
    public List<UserModel> users;
    
    public RoleModel() {}

    public RoleModel(String name) {
        this.name = name;
    }

    public RoleModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
