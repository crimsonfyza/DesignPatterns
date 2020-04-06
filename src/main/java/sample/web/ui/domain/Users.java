package sample.web.ui.domain;

import org.springframework.beans.factory.annotation.Autowired;
import sample.web.ui.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Users {
    private UserRepository userRepository;
    private List<UserAccount> accountList = new ArrayList<UserAccount>();

    @Autowired
    public void loadUsers (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public Iterable<UserAccount> getAllUsers (UserRepository userRepository) {
//        Iterable<UserAccount> listvalues = userRepository.findAll();
//        return listvalues;
//
//    }

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
