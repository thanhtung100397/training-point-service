package com.kthdv.training_point.controllers;

import com.kthdv.training_point.models.dto.UserDto;
import com.kthdv.training_point.models.response.UserIdentify;
import com.kthdv.training_point.services.UserAuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private UserAuthService userAuthService;

    @ApiOperation(value = "Create new account", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Create account success"),
            @ApiResponse(code = 409, message = "Account exist")
    })
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDto userDto) {
        return userAuthService.createNewUser(userDto);
    }

    @ApiOperation(value = "Login", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Login success", response = UserIdentify.class),
            @ApiResponse(code = 404, message = "Account not registered"),
            @ApiResponse(code = 403, message = "Wrong password")
    })
    @PostMapping("/login")
    public ResponseEntity<UserIdentify> login(@RequestParam("username") String username,
                                              @RequestParam("password") String password) {
        return userAuthService.login(username, password);
    }
}
