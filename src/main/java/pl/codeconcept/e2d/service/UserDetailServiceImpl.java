package pl.codeconcept.e2d.service;

import pl.codeconcept.e2d.Model.Role;
import pl.codeconcept.e2d.Model.Users;
import pl.codeconcept.e2d.repoitory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service ("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    public Users save(Users users) {
        return userRepo.save(users);
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("Invalid username"));


        HashSet<GrantedAuthority> getAuthority = new HashSet<>();
        for (Role role :user.getRole()){
            getAuthority.add(new SimpleGrantedAuthority(role.getRoleName().name()));
        }

        return new User(user.getLogin(), user.getPassword(), getAuthority);
    }
}
