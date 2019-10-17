package pl.codeconcept.e2d.Controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.codeconcept.e2d.Model.Users;
import pl.codeconcept.e2d.Secure.JwtToken;
import pl.codeconcept.e2d.repoitory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtToken jwtToken;


    @PostMapping("/singup")
    public Users registerUser ( @RequestBody Users users){

        Users newUsers = new Users(users.getUsername(),encoder.encode(users.getPassword()),users.getRole());
        return userRepo.save(users);
    }

    @PostMapping("/singin")
    public String login (@RequestBody Users users){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        users.getUsername(),
                        users.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtToken.generateJwtToken(authentication);
    }



}
