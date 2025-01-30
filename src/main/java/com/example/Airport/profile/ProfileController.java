package com.example.Airport.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-endpoint}")
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileRepository profileRepository;

    public ProfileController(ProfileService profileService,
                             ProfileRepository profileRepository) {
        this.profileService = profileService;
        this.profileRepository = profileRepository;
    }

    @GetMapping("/profile")
    public ResponseEntity<List<ProfileResponse>> getAllProfiles() {
        List<ProfileResponse> profileResponseList = profileService.getProfiles();
        return new ResponseEntity<>(profileResponseList, HttpStatus.OK);
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
