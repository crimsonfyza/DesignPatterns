package sample.web.ui.domain;


import sample.web.ui.repository.AccountStrategy;

public class TeacherAccount implements AccountStrategy {
    @Override
    public UserAccount determineRole(String username, int typeNumber, String phoneNumber, String Email) {
        UserAccount userAccount = new UserAccount(username, "Teacher", "EmployeeNumber",typeNumber, phoneNumber, Email);
        return userAccount;
    }
}