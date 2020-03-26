package sample.web.ui.domain;

public class Topic {

private Newspaper topic;

        public void initiateTopic() {
            topic = new Newspaper();
        }

        public void addSubscriber (String uniqueUserKey){
            Observer subscriber = new NewspaperSubscriber(uniqueUserKey);
            topic.register(subscriber);
            subscriber.setSubject(topic);
        }

        public void postMessage (String message) {
            topic.postMessage(message);
        }


}