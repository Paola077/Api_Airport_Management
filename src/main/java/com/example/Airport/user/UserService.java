package com.example.Airport.user;

import com.example.Airport.profile.Profile;
import com.example.Airport.profile.ProfileMapper;
import com.example.Airport.profile.ProfileService;
import com.example.Airport.profile.UserProfileResponse;
import com.example.Airport.reservation.*;
import com.example.Airport.reservation.exceptions.UserNotFoundException;
import com.example.Airport.role.Role;
import com.example.Airport.role.RoleRepository;
import com.example.Airport.role.RoleService;
import com.example.Airport.role.UserRoleResponse;
import com.example.Airport.role.exceptions.RoleNotFoundException;
import com.example.Airport.user.exceptions.UserAlreadyExistException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final ReservationRepository reservationRepository;

    public UserService(UserRepository userRepository, ProfileService profileService, PasswordEncoder passwordEncoder, RoleService roleService, RoleRepository roleRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.profileService = profileService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.roleRepository = roleRepository;

        this.reservationRepository = reservationRepository;
    }

    public UserProfileResponse createUser(UserRequest userRequest) {
        Optional<User> existUser = userRepository.findByEmail(userRequest.email());
        if(existUser.isPresent())
            throw new UserAlreadyExistException("User already exist with this email" + userRequest.email());

        User user = UserMapper.toEntity(userRequest, passwordEncoder);

        Set<Role> userRole =Set.of(roleService.assignDefaultRole());
        user.setRoles(userRole);

        User savedUser = userRepository.save(user);

        Profile profile = profileService.createProfile(savedUser);

        return new UserProfileResponse(
                UserMapper.toResponse(savedUser),
                ProfileMapper.toResponse(profile)
        );
    }

    public List<UserWithReservationsResponse> findAll() {
        List<User> userList = userRepository.findAll();

        if (userList.isEmpty()) {
            throw new UserNotFoundException("There is not users to show");
        }

        return userList.stream()
                .map(user -> {
                    List<ReservationResponse> reservations = getReservationsByUser(user.getId());
                    return UserMapper.toUserWithReservations(user, reservations);
                })
                .collect(Collectors.toList());
    }

    private List<ReservationResponse> getReservationsByUser(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);

        return reservations.stream()
                .map(ReservationMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserRoleResponse updateUserRole(String email, String newRole) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        Role role = roleRepository.findByName(newRole)
                .orElseThrow(() -> new RoleNotFoundException("Role not found: " + newRole));

        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }

        user.getRoles().clear();
        user.getRoles().add(role);

        User updatedUser = userRepository.save(user);

        return new UserRoleResponse(updatedUser.getEmail(), updatedUser.getRoles().iterator().next().getName());
    }

    @Transactional
    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));

        profileService.deleteProfileByUserId(id);
        userRepository.delete(existingUser);
    }
}
