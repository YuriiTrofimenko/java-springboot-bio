/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tyaa.springboot.hw.bio.repositories;

import org.springframework.data.repository.CrudRepository;
import org.tyaa.springboot.hw.bio.entities.User;

/**
 *
 * @author student
 */
public interface UserRepository extends CrudRepository<User, Long>{
    User findByName(String name);
}
