package com.thefirstrow.dreammate.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SequenceCreateRequest {

    private String stageTitle;
    private String stageContent;
    private Long musicMs;
    private String targetObject;
    private Long effectNumber;

    private String cameraName;
    private Float x;
    private Float y;
    private Float z;

}
