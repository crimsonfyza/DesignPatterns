package sample.web.ui.repository;


import sample.web.ui.domain.UserAccount;

public interface AccountStrategy {
    public UserAccount determineRole(String username, int typeNumber);
}