package com.example.Airport.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-endpoint}")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public ResponseEntity<List<ProfileResponse>> getAllProfiles() {
        List<ProfileResponse> profileResponseList = profileService.getProfiles();
        return new ResponseEntity<>(profileResponseList, HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileResponse> getProfileById(@PathVariable Long id) {
        ProfileResponse profileResponse = profileService.getProfileById(id);
        return new ResponseEntity<>(profileResponse, HttpStatus.OK);
    }

    @GetMapping("/profile/search")
    public ResponseEntity<ProfileResponse> getProfileByEmail(@RequestParam String email) {
        ProfileResponse profile = profileService.getProfileByEmail(email);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/profile/user/{userId}")
    public ResponseEntity<ProfileResponse> getProfileByUserId(@PathVariable Long userId) {
        ProfileResponse profile = profileService.getProfileByUserId(userId);
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<ProfileUpdateResponse> updateProfile(@PathVariable Long id,
                                                               @RequestBody ProfileUpdateRequest request) {
        ProfileUpdateResponse profileUpdateResponse = profileService.updateProfileByUser(id, request);
        return new ResponseEntity<>(profileUpdateResponse, HttpStatus.OK);
    }
}
