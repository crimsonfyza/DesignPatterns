package sample.web.ui.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SMS implements Communication {

    @Override
    public NotificationObject send(String name, String to, String message) {
        String fullMessage = "SMS send to: " + name + " " + " " + to + " " + message;
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        NotificationObject notificationObject = new NotificationObject(timeStamp, fullMessage);
        return notificationObject;
    }
}
