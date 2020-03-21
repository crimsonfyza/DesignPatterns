package sample.web.ui.domain;

import sample.web.ui.domain.NotificationObject;

public interface Notification {

    public NotificationObject notify(String name, String email, String telnr, String message);
}
