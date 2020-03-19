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
public class UserAccount {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String userRole;

    private String type;

    private int typeNumber;

    private String phoneNumber;

    private String Email;

    public UserAccount(String username, String userRole, String type, int typeNumber, String phoneNumber,String Email) {
        this.username = username;
        this.userRole = userRole;
        this.type = type;
        this.typeNumber = typeNumber;
        this.phoneNumber = phoneNumber;
        this.Email = Email;
    }

}