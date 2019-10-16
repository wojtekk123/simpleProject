package pl.codeconcept.e2d.Controller;

import pl.codeconcept.e2d.Model.Users;
import pl.codeconcept.e2d.service.SecureService;
import pl.codeconcept.e2d.service.UserDetailServiceImpl;
import pl.codeconcept.e2d.service.UserSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserDetailServiceImpl userDetailServiceImpl;

    @Autowired
    private UserSerive userSerive;
    @Autowired
    SecureService secureService;


    @PostMapping("/registration")
    public void registration (@ModelAttribute("User") Users users ){
        userSerive.save(users);
        secureService.autoLogin(users.getLogin(),users.getPassword());
    }

    @GetMapping("/login")
    public String login (){
        return "login";
    }


}
