package sample.web.ui.domain;


import sample.web.ui.repository.AccountStrategy;

public class TeacherAccount implements AccountStrategy {
    @Override
    public UserAccount determineRole(String username, int typeNumber) {
        UserAccount userAccount = new UserAccount(username, "Teacher", "EmployeeNumber",typeNumber);
        return userAccount;
    }
}