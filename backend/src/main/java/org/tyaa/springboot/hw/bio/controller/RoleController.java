package org.tyaa.springboot.hw.bio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyaa.springboot.hw.bio.model.JsonHttpResponse;
import org.tyaa.springboot.hw.bio.services.AuthService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private AuthService authService;

    @GetMapping("")
    public JsonHttpResponse getAll() {

        return authService.getRoles();
    }
}
