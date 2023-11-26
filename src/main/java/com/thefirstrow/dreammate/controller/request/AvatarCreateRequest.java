package com.thefirstrow.dreammate.controller.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AvatarCreateRequest {

    private String gender;
    private String top;
    private String bottom;
    private String shoes;
}
