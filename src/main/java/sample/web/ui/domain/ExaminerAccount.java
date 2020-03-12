package sample.web.ui.domain;


import sample.web.ui.repository.AccountStrategy;

public class ExaminerAccount implements AccountStrategy {
    @Override
    public UserAccount determineRole(String username,int typeNumber) {
        UserAccount userAccount = new UserAccount(username, "Examiner", "ExaminerNumber",typeNumber);
        return userAccount;
    }
}