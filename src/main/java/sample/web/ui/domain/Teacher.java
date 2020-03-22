package sample.web.ui.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Name is required.")
    private String name = null;




}
