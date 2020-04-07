package sample.web.ui.domain;

import org.springframework.beans.factory.annotation.Autowired;
import sample.web.ui.repository.OldUserRepository;
import sample.web.ui.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Users {
    private OldUserRepository oldUserRepository;
    private List<UserAccount> accountList = new ArrayList<UserAccount>();

    @Autowired
    public void loadUsers (OldUserRepository oldUserRepository) {
        this.oldUserRepository = oldUserRepository;
    }

    public Iterable<UserAccount> getAllUsers (OldUserRepository oldUserRepository) {
        Iterable<UserAccount> listvalues = oldUserRepository.findAll();
        return listvalues;

    }

    public void deleteUserById () {

    }

    public List<UserAccount> getAllSubscribers(Iterable<UserAccount> accountList) {

        for (UserAccount account : accountList) {
            if (account.getSubscribed() == true ) {
                this.accountList.add(account);
            }
        }

        return this.accountList;

    }

}
