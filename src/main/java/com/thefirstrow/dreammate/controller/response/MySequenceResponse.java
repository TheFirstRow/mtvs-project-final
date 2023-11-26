package com.thefirstrow.dreammate.controller.response;

import com.thefirstrow.dreammate.model.Avatar;
import com.thefirstrow.dreammate.model.Sequence;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class MySequenceResponse {

    private Long id;
    private String stageTitle;
    private String stageContent;
    private Long musicMs;
    private String targetObject;
    private Long effectNumber;

    private String cameraName;
    private Float x;
    private Float y;
    private Float z;

    private UserResponse user;
    private Timestamp registeredAt;
    private Timestamp updatedAt;


    public static MySequenceResponse fromSequence(Sequence sequence) {
        return new MySequenceResponse(
                sequence.getId(),
                sequence.getStageTitle(),
                sequence.getStageContent(),
                sequence.getMusicMs(),
                sequence.getTargetObject(),
                sequence.getEffectNumber(),
                sequence.getCameraName(),
                sequence.getX(),
                sequence.getY(),
                sequence.getZ(),
                UserResponse.fromUser(sequence.getUser()),
                sequence.getRegisteredAt(),
                sequence.getUpdatedAt()
        );
    }
}
