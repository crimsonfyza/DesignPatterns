package sample.web.ui.domain;

import sample.web.ui.domain.NotificationObject;

public interface Communication {
    public NotificationObject send(String name, String to, String message);
}
