package com.thefirstrow.dreammate.controller;



import com.thefirstrow.dreammate.controller.request.UserJoinRequest;
import com.thefirstrow.dreammate.controller.request.UserLoginRequest;
import com.thefirstrow.dreammate.controller.response.Response;
import com.thefirstrow.dreammate.controller.response.UserJoinResponse;
import com.thefirstrow.dreammate.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@Valid @RequestBody UserJoinRequest request) {
        return Response.success(UserJoinResponse.fromUser(userService.join(request.getEmail(), request.getNickname(), request.getPassword(), request.getConfirmPassword())));
    }

    @PostMapping("/login")
    public Response<String> login(@RequestBody UserLoginRequest request) {
        String token = userService.login(request.getEmail(), request.getPassword());
        return Response.success(token);
    }

    @GetMapping("/me")
    public Response<UserJoinResponse> me(Authentication authentication) {
        return Response.success(UserJoinResponse.fromUser(userService.loadUserByUsername(authentication.getName())));
    }

}
