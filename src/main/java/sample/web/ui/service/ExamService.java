package sample.web.ui.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;
import sample.web.ui.domain.Exam;
import sample.web.ui.domain.Grade;
import sample.web.ui.domain.User;
import sample.web.ui.repository.ExamRepository;
import sample.web.ui.repository.GradeRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final GradeRepository gradeRepository;

//    public Iterator<Exam> findNotEnrolled(User student) {
//        Iterable<Exam> exams = examRepository.findAll();
//        Iterable<Grade> grades = gradeRepository.findAllByStudent(student);
//        Iterator<Exam> notEnrolledExams = examRepository.findById("1");
//
//        while (notEnrolledExams.hasNext()) {
//            Exam exam = notEnrolledExams.next();
//            for (Grade grade : grades) {
//                if (grade.getExam().equals(exam)) {
//                    notEnrolledExams.remove();
//                }
//            }
//        }
//
//        return notEnrolledExams;
//    }

}