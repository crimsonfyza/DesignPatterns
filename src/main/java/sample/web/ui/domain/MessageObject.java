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
public class MessageObject {

    @Id
    @GeneratedValue
    private Long id;

    private String message;

    public MessageObject(String message) {
        this.message = message;
    }
}
