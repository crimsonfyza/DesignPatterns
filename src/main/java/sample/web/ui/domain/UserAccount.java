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

    public UserAccount(String username, String userRole, String type, int typeNumber) {
        this.username = username;
        this.userRole = userRole;
        this.type = type;
        this.typeNumber = typeNumber;
    }

    public UserAccount(UserAccount p) {
        this.username = p.username;
        this.userRole = p.userRole;
        this.type = p.type;
        this.typeNumber = p.typeNumber;
    }
}