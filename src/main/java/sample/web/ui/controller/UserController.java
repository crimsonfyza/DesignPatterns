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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.web.ui.domain.*;
import sample.web.ui.repository.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("registration")

public class UserController {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    // constructor dependency injection
	public UserController(
                             UserRepository userRepository,
                             NotificationRepository notificationRepository
    ) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
	}

    @Transactional
    @GetMapping
    public ModelAndView createForm(@ModelAttribute UserAccount userAccount){
        return new ModelAndView("registration/form", "userAccount", userAccount);
    }

    @PostMapping
    public ModelAndView create(@Valid UserAccount userAccount, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("/registration/form", "formErrors", result.getAllErrors());
        }

        System.out.println(userAccount);
        System.out.println(userAccount.getUserRole());
        System.out.println(userAccount);

        String userRole = userAccount.getUserRole();


        switch (userAccount.getUserRole()) {
            case "Teacher":
                TeacherAccount teacherAccount = new TeacherAccount();
                userRepository.save(teacherAccount.determineRole(userAccount.getUsername(),userAccount.getTypeNumber() , userAccount.getPhoneNumber() , userAccount.getEmail(), userAccount.getSubscribed()));
                break;
            case "Student":
                StudentAccount studentAccount = new StudentAccount();
                userRepository.save(studentAccount.determineRole(userAccount.getUsername(),userAccount.getTypeNumber() , userAccount.getPhoneNumber() , userAccount.getEmail(), userAccount.getSubscribed()));
                break;
            case "Examiner":
                ExaminerAccount examinerAccount = new ExaminerAccount();
                userRepository.save(examinerAccount.determineRole(userAccount.getUsername(),userAccount.getTypeNumber() , userAccount.getPhoneNumber() , userAccount.getEmail(), userAccount.getSubscribed()));
                break;
            default:

        }

        //ADAPTER PATTERN
        NotificationAdapter notificationAdapter = new NotificationAdapter(userAccount.getEmail(), userAccount.getPhoneNumber());
        NotificationObject notificationObject = notificationAdapter.notify(userAccount.getUsername(), userAccount.getEmail(), userAccount.getPhoneNumber(), "Het bericht wordt verstuurd");
        notificationRepository.save(notificationObject);

        return new ModelAndView("redirect:/registration", "userAccount", userAccount);
    }

}
