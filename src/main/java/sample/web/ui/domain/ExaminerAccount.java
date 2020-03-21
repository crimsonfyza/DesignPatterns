package sample.web.ui.domain;


public class ExaminerAccount implements AccountStrategy {
    @Override
    public UserAccount determineRole(String username,int typeNumber, String phoneNumber, String Email) {
        UserAccount userAccount = new UserAccount(username, "Examiner", "ExaminerNumber",typeNumber, phoneNumber, Email);
        return userAccount;
    }
}