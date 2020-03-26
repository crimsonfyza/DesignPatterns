package sample.web.ui.domain;


public class TeacherAccount implements AccountStrategy {
    @Override
    public UserAccount determineRole(String username, int typeNumber, String phoneNumber, String email, Boolean subscribed ) {
        UserAccount userAccount = new UserAccount(username, "Teacher", "EmployeeNumber",typeNumber, phoneNumber, email, subscribed );
        return userAccount;
    }
}