package com.example.Airport.user;
import com.example.Airport.airport.AirportResponse;
import com.example.Airport.role.UserRoleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-endpoint}")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/get-all")
    public ResponseEntity<List<UserWithReservationsResponse>> getAllUser() {
        List<UserWithReservationsResponse> userWithReservationsResponse = userService.findAll();
        return new ResponseEntity<>(userWithReservationsResponse, HttpStatus.OK);
    }


    @PutMapping("/user/update-role")
    public ResponseEntity<UserRoleResponse> updateUserRole(@RequestParam String email, @RequestParam String newRole) {
        UserRoleResponse response = userService.updateUserRole(email, newRole);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User has been eliminated", HttpStatus.OK);
    }
}
