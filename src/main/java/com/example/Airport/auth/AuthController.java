package com.example.Airport.auth;

import com.example.Airport.profile.UserProfileResponse;
import com.example.Airport.user.UserRequest;
import com.example.Airport.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api-endpoint}")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/public/register")
    public ResponseEntity<UserProfileResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        UserProfileResponse response = userService.createUser(userRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/public/login")
    public ResponseEntity<LoginResponse> login() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        String email = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().toString();

        LoginResponse loginResponse = new LoginResponse(email, role);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
