package pl.codeconcept.e2d.repoitory;

import pl.codeconcept.e2d.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {

  Users findByUsername(String username);
  Boolean existsByUsername(String username);


}
