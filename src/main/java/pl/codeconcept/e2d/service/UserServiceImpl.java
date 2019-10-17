package pl.codeconcept.e2d.service;

import pl.codeconcept.e2d.Model.Users;
import pl.codeconcept.e2d.repoitory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl   {

    @Autowired
    UserRepo userRepo;

    public Users saveUsers(Users users){
        return userRepo.save(users);
    }


}


