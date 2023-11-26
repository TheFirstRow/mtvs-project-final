package com.thefirstrow.dreammate.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AvatarUpdateRequest {

    private String gender;
    private String top;
    private String bottom;
    private String shoes;
}
