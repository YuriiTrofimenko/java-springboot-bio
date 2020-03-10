package org.tyaa.springboot.hw.bio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.tyaa.springboot.hw.bio.model.JsonHttpResponse;
import org.tyaa.springboot.hw.bio.model.UserModel;
import org.tyaa.springboot.hw.bio.services.AuthService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AuthService authService;

    @GetMapping("")
    public JsonHttpResponse getAll() {

        return authService.readUser();
    }

    @GetMapping(value = "/{id}")
    public JsonHttpResponse get(@PathVariable("id") Long _id) throws Exception {

        return authService.readUser(_id);
    }

    /*@GetMapping(value = "/get-by-name/{name}")
    public JsonHttpResponse getByName(@PathVariable("name") String _name) throws Exception {

        return authorService.read(_name);
    }*/

    @PostMapping("/create")
    public JsonHttpResponse create(@RequestBody UserModel _user) throws Exception {
        return authService.createUser(_user);
    }

    /*@PostMapping("/update")
    public JsonHttpResponse update(@RequestBody Author _author) {
        return authorService.update(_author);
    }*/

    /* @DeleteMapping(value = "/delete/{id}")
    public JsonHttpResponse delete(@PathVariable("id") Long _id) {

        return authService.deleteUser(_id);
    } */

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ResponseBody
    public JsonHttpResponse checkUser(Authentication authentication) {

        return authService.check(authentication);
    }

    @GetMapping("/onsignout")
    public JsonHttpResponse onsignout(HttpSession httpSession) {
        // httpSession.removeAttribute("ACCOUNT_INFO");
        /*Cart cart = (Cart) httpSession.getAttribute("CART");
        if (cart == null) {
            httpSession.removeAttribute("CART");
        }*/
        return authService.onSignOut();
    }

    @GetMapping("/onerror")
    public JsonHttpResponse onerror() {

        return authService.onError();
    }
}
