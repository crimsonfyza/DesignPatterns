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

import javax.validation.Valid;
import java.util.*;


@Controller
public class UserController {

    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

//    @Autowired
//    private UserValidator userValidator;

    @Autowired
    public UserController(
            RoleRepository roleRepository
    ) {
        this.roleRepository = roleRepository;
    }


    @Transactional
    @GetMapping(value = "registration")
    public ModelAndView createForm(@ModelAttribute User user){
//        List<String> roles = new ArrayList<String>();
//        roles.add("Student");
//        roles.add("Teacher");
//        roles.add("Examinator");
//        model.addAttribute("roles", roles);
//        model.addAttribute("userForm", new User());
        return new ModelAndView("registration", "user", user);
    }

//    @RequestMapping(value = "/registration", method = RequestMethod.GET)
//    public String registration(Model model) {
//        List<String> roles = new ArrayList<String>();
//        roles.add("Student");
//        roles.add("Teacher");
//        roles.add("Examinator");
//        model.addAttribute("roles", roles);
//        model.addAttribute("userForm", new User());
//        //model.addAttribute("roleForm", new Role());
//        return "registration";
//    }

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


//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
//        Role userRole = new Role();
//        List<Role> roles = roleRepository.findAll();
//        userRole.setName(userForm.getRole());
//        for (Role role : roles) {
//            if (role.getName().equals(userRole.getName())) {
//                userRole.setId(role.getId());
//            }
//        }
//        Set<Role> userRoles = new TreeSet<Role>(new RoleComp());
//        userRoles.add(userRole);
//        userForm.setRoles(userRoles);
//        userService.save(userForm);
//        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
//
//        return "redirect:/welcome";
//
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
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
