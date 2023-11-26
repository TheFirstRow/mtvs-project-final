package com.thefirstrow.dreammate.model;

import com.thefirstrow.dreammate.model.entity.AvatarEntity;
import com.thefirstrow.dreammate.model.entity.SequenceEntity;
import com.thefirstrow.dreammate.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sequence {

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

    private User user;
    private Timestamp registeredAt;
    private Timestamp updatedAt;
    private Timestamp removedAt;

    public static Sequence fromEntity(SequenceEntity entity) {
        return new Sequence(
                entity.getId(),
                entity.getStageTitle(),
                entity.getStageContent(),
                entity.getMusicMs(),
                entity.getTargetObject(),
                entity.getEffectNumber(),
                entity.getCameraName(),
                entity.getX(),
                entity.getY(),
                entity.getZ(),
                User.fromEntity(entity.getUser()),
                entity.getRegisteredAt(),
                entity.getUpdatedAt(),
                entity.getRemovedAt()
        );
    }
}
