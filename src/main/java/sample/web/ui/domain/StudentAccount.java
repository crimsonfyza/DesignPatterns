package sample.web.ui.domain;

public class StudentAccount implements AccountStrategy {
    @Override
    public UserAccount determineRole(String username,int typeNumber,String phoneNumber, String email, Boolean subscribed) {
        UserAccount userAccount = new UserAccount(username, "Student", "StudentNumber", typeNumber, phoneNumber, email, subscribed);
        return userAccount;
    }
}