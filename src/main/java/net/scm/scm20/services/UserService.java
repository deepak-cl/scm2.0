package net.scm.scm20.services;


import java.util.List;
import java.util.Optional;

import net.scm.scm20.entity.User;

public interface UserService {
   User saveUser(User user);
   Optional<User> getUserById(String id);
   Optional<User> updateUser(User user);
   void deleteUser(String id);
   boolean isUserExist(String userId);
   boolean isUserEXistByEmail(String email);
   List<User> getAllUsers();
}
