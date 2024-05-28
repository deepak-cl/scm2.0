package net.scm.scm20.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.scm.scm20.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  // Custom query methods 
  // Cutom finder methods

  Optional<User> findByEmail(String email);

  Optional<User> findByEmailAndPassword(String email, String password);
}
