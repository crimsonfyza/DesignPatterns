package sample.web.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.web.ui.domain.Topic;
import sample.web.ui.domain.UserAccount;
import sample.web.ui.domain.Users;
import sample.web.ui.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/")

public class TopicController {
    private final UserRepository userRepository;

    public TopicController (
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void iniateData() {

        //PREPARE FOR NEW OBJECT
        Users user = new Users(userRepository);
        Iterable<UserAccount> test =  user.getAllUsers();

        List<UserAccount> temp = user.getAllSubscribers(test);

        Topic topic = new Topic();
        topic.initiateTopic();

        for (UserAccount current :temp ) {
           String uniq = current.getId() + current.getUsername();
            topic.addSubscriber(uniq);
        }

        topic.postMessage("message has been added.");

    }

}
