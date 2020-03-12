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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import sample.web.ui.domain.*;
import sample.web.ui.repository.*;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/")
public class MessageController {

    private final UserRepository userRepository;

    // constructor dependency injection
	@Autowired
	public MessageController(
                             UserRepository userRepository
    ) {
        this.userRepository = userRepository;
	}


    @Autowired
    private void createUser() {

        StudentAccount studentAccount = new StudentAccount();
        userRepository.save(studentAccount.determineRole("kevin", 67867867));

        TeacherAccount teacherAccount = new TeacherAccount();
        userRepository.save(teacherAccount.determineRole("Michael", 23523523));

        ExaminerAccount examinerAccount = new ExaminerAccount();
        userRepository.save(examinerAccount.determineRole("Aron", 235235213));

    }

}
