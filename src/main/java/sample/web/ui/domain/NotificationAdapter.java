package sample.web.ui.domain;

import sample.web.ui.repository.Communication;
import sample.web.ui.repository.Notification;

public class NotificationAdapter implements Notification {


    Communication communicationMail;
    Communication communicationSMS;

    public NotificationAdapter(String email, String telnr) {

        try {
            if (email != null && telnr != null) {
                communicationMail = new Mail();
                communicationSMS = new SMS();
            }
            else if (email != null) {
                communicationMail = new Mail();
            }
            else if (telnr != null) {
                communicationSMS = new SMS();
            }
            else {
                communicationMail = new Mail();
            }
        } catch (Exception e) {
            System.out.println("Failed to send notification, please check with you Administrator");
        }
    }

    @Override
    public NotificationObject notify(String name, String email, String telnr, String message) {
        NotificationObject notificationObject = null;

        try {

            if (email != null) {
                notificationObject = communicationMail.send(name, email, message);
            }
            else if (telnr != null && notificationObject == null) {
                notificationObject = communicationSMS.send(name, telnr, message);
            }
            else if (notificationObject == null) {
                System.out.println("Overig");
                String defaultEmail = "admin@admin.com";
                String defaultMessage = "Notification send to Administrator";
                notificationObject = communicationMail.send(name, defaultEmail, defaultMessage);
            }

        } catch (Exception e) {
            System.out.println("Failed to send notification, please check with you Administrator");
        }

        return notificationObject;

    }
}

