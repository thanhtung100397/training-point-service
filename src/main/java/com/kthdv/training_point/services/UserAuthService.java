package com.kthdv.training_point.services;

import com.kthdv.training_point.dao.UserRepository;
import com.kthdv.training_point.models.entity.User;
import com.kthdv.training_point.models.dto.UserDto;
import com.kthdv.training_point.models.response.UserIdentify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity createNewUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        switch (userDto.getRole()) {
            case User.MONITOR_ROLE: {
                if (userRepository.existsByRole(User.MONITOR_ROLE)) {
                    return new ResponseEntity(HttpStatus.FORBIDDEN);
                }
            }
            break;

            case User.ADVISER_ROLE: {
                if (userRepository.existsByRole(User.ADVISER_ROLE)) {
                    return new ResponseEntity(HttpStatus.FORBIDDEN);
                }
            }
            break;

            default: {
                break;
            }
        }
        userRepository.save(new User(userDto));
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<UserIdentify> login(String username, String password) {
        User userFound = userRepository.findByUsername(username);
        if (userFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (userFound.getPassword().equals(password)) {
            return new ResponseEntity<>(new UserIdentify(userFound.getId(), userFound.getRole()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public boolean isUserValid(String userID, String... requiredRoles) {
        return userRepository.existsByIdAndRoleIn(userID, requiredRoles);
    }
}
