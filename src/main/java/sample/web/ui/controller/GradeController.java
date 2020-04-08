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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.web.ui.domain.*;
import sample.web.ui.repository.ExamRepository;
import sample.web.ui.repository.GradeRepository;

import java.util.Date;


@Controller
@RequestMapping("/")
public class GradeController {

//    private final ExamRepository examRepository;
//    private final GradeRepository gradeRepository;
//
//
//    // constructor dependency injection
//	@Autowired
//	public GradeController(
//                             ExamRepository examRepository,
//                             GradeRepository gradeRepository) {
//        this.examRepository = examRepository;
//        this.gradeRepository = gradeRepository;
//
//	}


    @Autowired
    private void createProductCatalogAndProducts() {

//        //Exam factory design pattern
//        Exam writtenExam = ExamFactory.getExam(ExamType.WRITTEN);
//
//        System.out.println("Dit is de class: " + writtenExam.getClass());
//        writtenExam.setDate(new Date());
//        writtenExam.createExam();
//        writtenExam.setName("ADS");
//
//        writtenExam = examRepository.save(writtenExam);
//        Long id = writtenExam.getId();
//        System.out.println("Dit is de datum: " + writtenExam.getDate());
//        if (writtenExam != null) {
//            writtenExam.createExam();
//        } else {
//            System.out.println("This exam can not be created.");
//        }
//
//        Exam computerExam = ExamFactory.getExam(ExamType.COMPUTER);
//        computerExam.setName("Netwerken");
//        ((ComputerExam) computerExam).setInternetAllowed(true);
//        ((ComputerExam) computerExam).setOwnComputer(true);
//        computerExam = examRepository.save(computerExam);
//        if (computerExam != null) {
//            computerExam.createExam();
//        } else {
//            System.out.println("This exam can not be created.");
//        }
//        Exam oralExam = ExamFactory.getExam(ExamType.ORAL);
//        oralExam.setName("Design patterns");
//        ((OralExam) oralExam).setNumberOfStudents(5);
//        oralExam = examRepository.save(oralExam);
//        if (oralExam != null) {
//            oralExam.createExam();
//        } else {
//            System.out.println("This exam can not be created.");
//        }
//        Iterable<Exam> exams = examRepository.findAll();
//
//        for(Exam exam : exams) {
//            System.out.println(exam.getName());
//        };


        // Grade Status State design pattern

//        Grade gradeVanKevin = new Grade();
//        gradeVanKevin.printStatus();
//        gradeVanKevin.nextState();
//        gradeVanKevin.printStatus();
//        gradeVanKevin.setResult(9.9);
//        gradeVanKevin.nextState();
//        gradeVanKevin.printStatus();
//        gradeVanKevin.setExam(oralExam);
//        gradeRepository.save(gradeVanKevin);
//
//        Grade gradeVanAron = new Grade();
//        gradeVanAron.setExam(writtenExam);
//        gradeVanAron.nextState();
//        gradeVanAron.setResult(6.6);
//        gradeRepository.save(gradeVanAron);
//
//        Iterable<Grade> grades = gradeRepository.findAll();
//
//        for(Grade grade : grades) {
//            System.out.println(grade.getResult());
//            grade.printStatus();
//            System.out.println(grade.getExam().getName());
//        };


    }


}
