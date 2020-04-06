package sample.web.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.web.ui.domain.*;
import sample.web.ui.repository.MessageRepository;
import sample.web.ui.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("topic")

public class TopicController {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private Topic topic;

    public TopicController (
            UserRepository userRepository,
            MessageRepository messageRepository
    ) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

//    public void iniateData() {
//
//        //PREPARE FOR NEW OBJECT
//        Users user = new Users();
//        Iterable<UserAccount> allUsers =  user.getAllUsers(userRepository);
//
//        List<UserAccount> accountList = user.getAllSubscribers(allUsers);
//
//        topic = new Topic();
//        topic.initiateTopic();
//
//        for (UserAccount current :accountList ) {
//            topic.addSubscriber(current.getId());
//        }
//
//    }

//    @Transactional
//    @GetMapping
//    public ModelAndView createForm(@ModelAttribute MessageObject messageObject){
//        iniateData();
//        return new ModelAndView("topics/form", "messageObject", messageObject);
//    }

    @PostMapping
    public ModelAndView create(@Valid MessageObject messageObject, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView("/topics/form", "formErrors", result.getAllErrors());
        }

        System.out.println(messageObject.getMessage());
        topic.postMessage(messageObject.getMessage());

        return new ModelAndView("redirect:/topic", "topic", messageObject);
    }

}
