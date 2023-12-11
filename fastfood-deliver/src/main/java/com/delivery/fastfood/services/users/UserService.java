package com.delivery.fastfood.services.users;

import com.delivery.fastfood.domain.entities.User;
import com.delivery.fastfood.domain.entities.orders.Order;
import com.delivery.fastfood.exception.NotFoundException;
import com.delivery.fastfood.repositories.OrderRepository;
import com.delivery.fastfood.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.orderRepository = orderRepository;
    }

    public User create(User user){
        User checkForUserExsists = userRepository.getById(user.getId());
        if(checkForUserExsists == null){
            throw new NotFoundException("User already exsists");
        }
        Order order = new Order();
        order.setUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        orderRepository.save(order);
        return savedUser;
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public User update(String userName, User updatedUserData) {
        User existingUser = userRepository.findByUserName(userName);

        if (existingUser == null) {
            throw new NotFoundException("User not found with username: " + userName);
        }

        existingUser.setUserName(updatedUserData.getUserName());
        existingUser.setPassword(updatedUserData.getPassword());
        existingUser.setFirstName(updatedUserData.getFirstName());
        existingUser.setLastName(updatedUserData.getLastName());
        existingUser.setRoles(updatedUserData.getRoles());

        return userRepository.save(existingUser);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Long id){
        User existingUser = userRepository.getById(id);
        if (existingUser == null) {
            throw new NotFoundException("User not found with this id");
        }
        return existingUser;
    }

    public Long getId(String username){
        User existingUser = userRepository.findByUserName(username);
        if (existingUser == null) {
            throw new NotFoundException("User not found with this id");
        }
        return existingUser.getId();
    }

    public Boolean checkUserName(String userName){
        return userRepository.existsByUserName(userName);
    }
}