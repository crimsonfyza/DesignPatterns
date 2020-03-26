package sample.web.ui.domain;


import sample.web.ui.domain.UserAccount;

public interface AccountStrategy {
    public UserAccount determineRole(String username, int typeNumber,String phoneNumber, String email, Boolean subscribed);
}