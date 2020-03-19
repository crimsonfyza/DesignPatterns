package sample.web.ui.repository;

import sample.web.ui.domain.NotificationObject;

public interface Notification {

    public NotificationObject notify(String name, String email, String telnr, String message);
}
