package sample.web.ui.domain;

import sample.web.ui.controller.TopicController;
import sample.web.ui.repository.UserRepository;

public class NewspaperSubscriber implements Observer {

    private Long id;
    private Subject topic;

    public NewspaperSubscriber(Long nm){
        this.id=nm;
    }
    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if(msg == null){
            System.out.println(id+": No new message");
        }else
            System.out.println(id+": "+msg);

    }

    @Override
    public void setSubject(Subject sub) {
        this.topic=sub;
    }

}