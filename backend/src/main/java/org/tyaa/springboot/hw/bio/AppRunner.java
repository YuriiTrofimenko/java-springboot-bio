/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.springboot.hw.bio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.tyaa.springboot.hw.bio.entities.Role;
import org.tyaa.springboot.hw.bio.entities.User;
import org.tyaa.springboot.hw.bio.repositories.RoleRepository;
import org.tyaa.springboot.hw.bio.repositories.UserRepository;

/**
 *
 * @author student
 */
@Component
public class AppRunner implements CommandLineRunner {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        Role adminRole =
            roleRepository.save(new Role("admin"));
	userRepository.save(new User("admin", "1", adminRole));
        Role userRole =
            roleRepository.save(new Role("user"));
	userRepository.save(new User("user2", "2", userRole));
    }
}
