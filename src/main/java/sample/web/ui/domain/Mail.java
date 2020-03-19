package sample.web.ui.domain;

import sample.web.ui.repository.Communication;

public class Mail implements Communication {

    @Override
    public void send(String name, String to, String message) {
        System.out.println("Mail send to: " + name + " " + " " + to + " " + message);
    }


}
