package pl.codeconcept.e2d.service;

import pl.codeconcept.e2d.Model.Users;
import pl.codeconcept.e2d.repoitory.RoleRepo;
import pl.codeconcept.e2d.repoitory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserSerive {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        users.setRole(new HashSet<>(roleRepo.findAll()));
        userRepo.save(users);

    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
