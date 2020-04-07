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
import sample.web.ui.service.SecurityService;
import sample.web.ui.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;


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


    @Transactional
    @GetMapping(value = "registration")
    public ModelAndView createForm(@ModelAttribute User user){
        return new ModelAndView("registration", "user", user);
    }



    @PostMapping(value = "registration")
    public ModelAndView create(@Valid User user, BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors()){
            return new ModelAndView("registration", "formErrors", result.getAllErrors());
        }

        Role userRole = new Role();
        List<Role> roles = roleRepository.findAll();
        userRole.setName(user.getRole());
        for (Role role : roles) {
            if (role.getName().equals(userRole.getName())) {
                userRole.setId(role.getId());
            }
        }
        Set<Role> userRoles = new TreeSet<Role>(new RoleComp());
        userRoles.add(userRole);
        user.setRoles(userRoles);
        userService.save(user);
        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        return new ModelAndView("redirect:/home", "user", user);
    }


    @Transactional
    @GetMapping(value = "login")
    public ModelAndView login(@ModelAttribute User user){
        return new ModelAndView("login", "user", user);
    }


    @PostMapping(value = "/login")
    public ModelAndView login(@Valid User user, BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors()){
            return new ModelAndView("login", "formErrors", result.getAllErrors());
        }
        securityService.autologin(user.getUsername(), user.getPassword());
        return new ModelAndView("redirect:/home", "user", user);
    }

    @GetMapping("/logout")
    public String fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        return "home";
    }

    class RoleComp implements Comparator<Role> {

        @Override
        public int compare(Role r1, Role r2) {
            return r1.getName().compareTo(r2.getName());
        }
    }
}


//@Controller
//@RequestMapping("registration")
//
//public class UserController {
//
//    private final UserRepository userRepository;
//    private final NotificationRepository notificationRepository;
//
//    // constructor dependency injection
//	public UserController(
//                             UserRepository userRepository,
//                             NotificationRepository notificationRepository
//    ) {
//        this.userRepository = userRepository;
//        this.notificationRepository = notificationRepository;
//	}
//
//    @Transactional
//    @GetMapping
//    public ModelAndView createForm(@ModelAttribute UserAccount userAccount){
//        return new ModelAndView("registration/form", "userAccount", userAccount);
//    }
//
//    @PostMapping
//    public ModelAndView create(@Valid UserAccount userAccount, BindingResult result, RedirectAttributes redirect) {
//        if (result.hasErrors()) {
//            return new ModelAndView("/registration/form", "formErrors", result.getAllErrors());
//        }
//
//        switch (userAccount.getUserRole()) {
//            case "Teacher":
//                TeacherAccount teacherAccount = new TeacherAccount();
//                userRepository.save(teacherAccount.determineRole(userAccount.getUsername(),userAccount.getTypeNumber() , userAccount.getPhoneNumber() , userAccount.getEmail(), userAccount.getSubscribed()));
//                break;
//            case "Student":
//                StudentAccount studentAccount = new StudentAccount();
//                userRepository.save(studentAccount.determineRole(userAccount.getUsername(),userAccount.getTypeNumber() , userAccount.getPhoneNumber() , userAccount.getEmail(), userAccount.getSubscribed()));
//                break;
//            case "Examiner":
//                ExaminerAccount examinerAccount = new ExaminerAccount();
//                userRepository.save(examinerAccount.determineRole(userAccount.getUsername(),userAccount.getTypeNumber() , userAccount.getPhoneNumber() , userAccount.getEmail(), userAccount.getSubscribed()));
//                break;
//            default:
//
//        }
//
//        //ADAPTER PATTERN
//        NotificationAdapter notificationAdapter = new NotificationAdapter(userAccount.getEmail(), userAccount.getPhoneNumber());
//        NotificationObject notificationObject = notificationAdapter.notify(userAccount.getUsername(), userAccount.getEmail(), userAccount.getPhoneNumber(), "Het bericht wordt verstuurd");
//        notificationRepository.save(notificationObject);
//
//        return new ModelAndView("redirect:/registration", "userAccount", userAccount);
//    }
//
//}
