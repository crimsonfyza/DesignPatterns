package sample.web.ui.domain;

public class NewspaperSubscriber implements Observer {

    private String name;
    private Subject topic;

    public NewspaperSubscriber(String nm){
        this.name=nm;
    }
    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if(msg == null){
            System.out.println(name+": No new message");
        }else
            System.out.println(name+": "+msg);
    }

    @Override
    public void setSubject(Subject sub) {
        this.topic=sub;
    }

}