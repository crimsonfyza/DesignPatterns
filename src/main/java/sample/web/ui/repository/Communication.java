package sample.web.ui.repository;

import sample.web.ui.domain.NotificationObject;

public interface Communication {
    public NotificationObject send(String name, String to, String message);
}
