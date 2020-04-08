package sample.web.ui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.Exam;
import sample.web.ui.domain.Grade;
import sample.web.ui.domain.User;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    Iterable<Grade> findAllByExam(Exam exam);
    Iterable<Grade> findAllByStudent(User student);
}
