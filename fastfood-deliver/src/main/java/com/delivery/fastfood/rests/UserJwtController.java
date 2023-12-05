package com.delivery.fastfood.rests;


import com.delivery.fastfood.domain.entities.User;
import com.delivery.fastfood.repositories.UserRepository;
import com.delivery.fastfood.rests.vm.LoginVm;
import com.delivery.fastfood.securities.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserJwtController {

    private  final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;
    public UserJwtController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginVm loginVM){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginVM.getUsername(),loginVM.getPassword()));
        User user = userRepository.findByUserName(loginVM.getUsername());
        if(user == null){
            throw new UsernameNotFoundException("this user does not exsists");
        }
        String token = jwtTokenProvider.createToken(user.getUserName(),user.getRoles());
        Map<Object,Object> map = new HashMap<>();
        map.put("username", user.getUserName());
        map.put("token", token);

        return ResponseEntity.ok(map);

    }


}
