package pl.codeconcept.e2d.service;

import pl.codeconcept.e2d.Model.Users;

import java.util.Optional;

public interface UserSerive {

    void save (Users users);

    Optional<Users> findByUsername (String username);


}
