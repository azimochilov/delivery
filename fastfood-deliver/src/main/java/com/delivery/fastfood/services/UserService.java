package com.delivery.fastfood.services;

import com.delivery.fastfood.domain.entities.User;
import com.delivery.fastfood.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository/*, PasswordEncoder passwordEncoder*/) {
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
    }

    public User create(User user){
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public User update(String userName, User updatedUserData) {
        User existingUser = userRepository.findByUserName(userName);

        if (existingUser == null) {
            throw new RuntimeException("User not found with username: " + userName);
        }

        existingUser.setUserName(updatedUserData.getUserName());
        existingUser.setPassword(updatedUserData.getPassword());
        existingUser.setFirstName(updatedUserData.getFirstName());
        existingUser.setLastName(updatedUserData.getLastName());
        existingUser.setRoles(updatedUserData.getRoles());

        return userRepository.save(existingUser);
    }

    public User getById(Long id){
        User existingUser = userRepository.getById(id);
        if (existingUser == null) {
            throw new RuntimeException("User not found with this id");
        }
        return existingUser;
    }

    public Boolean checkUserName(String userName){
        return userRepository.existsByUserName(userName);
    }
}
