/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package sample.web.ui.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.web.ui.domain.*;
import sample.web.ui.repository.*;

import org.springframework.stereotype.Controller;
import sample.web.ui.service.RoleService;
import sample.web.ui.service.SecurityService;
import sample.web.ui.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

/**
 * Controller for users.
 *
 * @author  Mark van Dalen
 *
 */


@Controller
public class UserController {

    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    public UserController(
            RoleRepository roleRepository
    ) {
        this.roleRepository = roleRepository;
    }


    //GET the registration form
    @Transactional
    @GetMapping(value = "registration")
    public ModelAndView createForm(@ModelAttribute User user){
        return new ModelAndView("registration", "user", user);
    }


    //POST a new user
    @PostMapping(value = "registration")
    public ModelAndView create(@Valid User user, BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors()){
            return new ModelAndView("registration", "formErrors", result.getAllErrors());
        }

        //get all the possible roles and find the matching ons
        Role userRole = new Role();
        List<Role> roles = roleRepository.findAll();
        userRole.setName(user.getRole());
        for (Role role : roles) {
            if (role.getName().equals(userRole.getName())) {
                userRole.setId(role.getId());
            }
        }
        Set<Role> userRoles = new TreeSet<Role>(new RoleService.RoleComp());

        //set the role for the new user
        userRoles.add(userRole);
        user.setRoles(userRoles);

        //save the new user
        userService.save(user);

        //log the new user in
        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        return new ModelAndView("redirect:/home", "user", user);
    }

    //GET login form
    @Transactional
    @GetMapping(value = "login")
    public ModelAndView login(@ModelAttribute User user){
        return new ModelAndView("login", "user", user);
    }

    //POST user login for authentication
    @PostMapping(value = "/login")
    public ModelAndView login(@Valid User user, BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors()){
            return new ModelAndView("login", "formErrors", result.getAllErrors());
        }

        //authenticate the user
        securityService.autologin(user.getUsername(), user.getPassword());
        return new ModelAndView("redirect:/home", "user", user);
    }

    //Logout for the user
    @GetMapping("/logout")
    public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/home";
    }

    //GET the homepage
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        return "home";
    }

}


