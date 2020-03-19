package sample.web.ui.domain;

import sample.web.ui.repository.Communication;

public class SMS implements Communication {

    @Override
    public void send(String name, String to, String message) {
        System.out.println("SMS send to: " + name + " " + " " + to + " " + message);
    }
}
