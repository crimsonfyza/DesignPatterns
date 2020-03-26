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
import sample.web.ui.domain.*;
import sample.web.ui.repository.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/")

public class UserController {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    // constructor dependency injection
	@Autowired
	public UserController(
                             UserRepository userRepository,
                             NotificationRepository notificationRepository
    ) {
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
	}

    @Autowired
    private void createUser() {

	    // USERINPUT from Web interface


        String type = "Teacher";
        String username = "Michael";
        int typeNumber = 23523523;
        String phoneNumber = "12312";
        String Email = "akbudwwakd";
        Boolean subscribed = true  ;

        //

       if (type == "Teacher") {
           TeacherAccount teacherAccount = new TeacherAccount();
           userRepository.save(teacherAccount.determineRole(username,typeNumber , phoneNumber , Email, subscribed));
       }

        if (type == "Student") {
            StudentAccount studentAccount = new StudentAccount();
            userRepository.save(studentAccount.determineRole(username,typeNumber , phoneNumber , Email, subscribed));
        }

        if (type == "Examiner") {
            ExaminerAccount examinerAccount = new ExaminerAccount();
            userRepository.save(examinerAccount.determineRole(username,typeNumber , phoneNumber , Email, subscribed));
        }


        //ADAPTER PATTERN
        NotificationAdapter notificationAdapter = new NotificationAdapter(Email, phoneNumber);
        NotificationObject notificationObject = notificationAdapter.notify(username, Email, phoneNumber, "Het bericht wordt verstuurd");
        notificationRepository.save(notificationObject);


    }

}
