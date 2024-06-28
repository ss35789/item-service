package hello.itemservice.web.basic;

import hello.itemservice.domain.User.User;
import hello.itemservice.domain.User.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/basic/users")
public class BasicUserController {
    private UserRepository userRepository;

    public BasicUserController(){
        registerUser("테스트유저", new Date("2024-06-24"));
    }

    @GetMapping
    public User registerUser(String name, Date birthday){
        User user = new User(name, birthday);
        userRepository.save(user);
        return user;
    }
}
