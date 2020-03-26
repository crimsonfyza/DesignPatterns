package sample.web.ui.domain;

import sample.web.ui.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class Users {
private UserRepository currentUserRepository;
    private List<UserAccount> accountList = new ArrayList<UserAccount>();

    public Users (UserRepository userRepository) {
        this.currentUserRepository = userRepository;
    }

    public Iterable<UserAccount> getAllUsers () {
        Iterable<UserAccount> listvalues = currentUserRepository.findAll();
        return listvalues;
    }

    public void getUserById () {

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
