package com.delivery.fastfood.rests;

import com.delivery.fastfood.domain.entities.User;
import com.delivery.fastfood.services.users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserResource {

    private  final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){
        if(!checkPasswordLength(user.getPassword())){
            return new ResponseEntity("Password's length less than 4 ", HttpStatus.BAD_REQUEST);
        }
        if(userService.checkUserName(user.getUserName())){
            return new ResponseEntity(" This user already exsists! ", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(userService.create(user));
    }

    private Boolean checkPasswordLength(String password){

        return (password.length()>4);
    }
}
