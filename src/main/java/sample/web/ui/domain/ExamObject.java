package sample.web.ui.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExamObject {

    @Id
    @GeneratedValue
    private Long id;

    private String examName;

    private String type;

    public ExamObject(String examName, String type) {
        this.examName = examName;
        this.type = type;
    }


}
