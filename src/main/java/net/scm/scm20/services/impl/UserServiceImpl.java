package net.scm.scm20.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.scm.scm20.entity.User;
import net.scm.scm20.exception.ResourceNotFoundException;
import net.scm.scm20.repositories.UserRepository;
import net.scm.scm20.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
       // UserId needs to be generated
       String userId = UUID.randomUUID().toString();
       user.setUserId(userId);
       // Password Encoder

       return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
       return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
       User user2 = userRepository.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

       // Update User
       user2.setName(user.getName());
       user2.setEmail(user.getEmail());
       user2.setPassword(user.getPassword());
       user2.setAbout(user.getAbout());
       user2.setPhoneNumber(user.getPhoneNumber());
       user2.setProfilePic(user.getProfilePic());
       user2.setEnabled(user.isEnabled());
       user2.setEmailVerified(user.isEmailVerified());
       user2.setPhoneVerified(user.isPhoneVerified());
       user2.setProvider(user.getProvider());
       user2.setProviderUserId(user.getProviderUserId());

       // Save the user in the database 
       User updatedUser = userRepository.save(user2);

       return Optional.ofNullable(updatedUser);
    }

    @Override
    public void deleteUser(String id) {
        User user2 = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user2);
        // Update User
    }

    @Override
    public boolean isUserExist(String userId) {
        User user2 = userRepository.findById(userId).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public boolean isUserEXistByEmail(String email) {
       User user = userRepository.findByEmail(email).orElse(null);
       return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
