package sample.web.ui.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor
public class NotificationObject {

    @Id
    @GeneratedValue
    private Long id;

    private String timeStamp;

    private String Message;

    public NotificationObject(String timeStamp, String Message) {
        this.timeStamp = timeStamp;
        this.Message = Message;
    }

}