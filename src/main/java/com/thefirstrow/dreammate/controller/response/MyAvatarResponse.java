package com.thefirstrow.dreammate.controller.response;

import com.thefirstrow.dreammate.model.Avatar;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class MyAvatarResponse {

    private Long id;
    private String gender;
    private String top;
    private String bottom;
    private String shoes;
    private UserResponse user;
    private Timestamp registeredAt;
    private Timestamp updatedAt;

    public static MyAvatarResponse fromAvatar(Avatar avatar) {
        return new MyAvatarResponse(
                avatar.getId(),
                avatar.getGender(),
                avatar.getTop(),
                avatar.getBottom(),
                avatar.getShoes(),
                UserResponse.fromUser(avatar.getUser()),
                avatar.getRegisteredAt(),
                avatar.getUpdatedAt()
        );
    }
}
