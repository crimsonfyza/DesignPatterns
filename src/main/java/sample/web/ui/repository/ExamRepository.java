package sample.web.ui.repository;

import org.springframework.data.repository.CrudRepository;
import sample.web.ui.domain.Exam;
import sample.web.ui.domain.Grade;
import sample.web.ui.domain.User;

import java.util.Iterator;

/**
 *
 * @author  Mark van Dalen
 *
 */

public interface ExamRepository extends CrudRepository<Exam, Long> {
    Iterable<Exam> findAllByTeacher(User teacher);
}
