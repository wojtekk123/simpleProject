package pl.codeconcept.e2d.repoitory;

import pl.codeconcept.e2d.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<Users,Long> {

    Optional<Users> findByUsername(String username);
    Boolean existsByUserName (String username);

}
