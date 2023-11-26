package com.thefirstrow.dreammate.controller.response;

import com.thefirstrow.dreammate.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public
class UserResponse {
    private Long id;
    private String email;

    public static UserResponse fromUser(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail()
        );
    }

}
