/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.springboot.hw.bio.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.tyaa.springboot.hw.bio.model.UserModel;
import org.tyaa.springboot.hw.bio.services.AuthService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author gachechega
 */
@Component
public class MySqlWebAuthProvider implements AuthenticationProvider {
    
    @Autowired
    private AuthService authService;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {

        String name = a.getName();
        String password = a.getCredentials().toString();

        // Logger.getLogger(ObjectifyWebAuthProvider.class.getName()).log(Level.SEVERE, null, "my2 - " + name);
        // Logger.getLogger(ObjectifyWebAuthProvider.class.getName()).log(Level.SEVERE, null, "my3 - " + password);
        UserModel userModel = null;
        try {
            userModel = authService.readUserByName(name).data;
        } catch (Exception ex) {
            Logger.getLogger(MySqlWebAuthProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (userModel != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            Logger.getLogger(MySqlWebAuthProvider.class.getName()).log(Level.SEVERE, null, "my - " + userModel.role);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userModel.role.name)); // name is a string

            return new UsernamePasswordAuthenticationToken(name, password, authorities);
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    
}