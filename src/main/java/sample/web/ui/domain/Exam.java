package sample.web.ui.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class Exam {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name is required.")
    private String name = null;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private User teacher;

    private ExamType type = null;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date = null;

    public abstract void createExam();
}
