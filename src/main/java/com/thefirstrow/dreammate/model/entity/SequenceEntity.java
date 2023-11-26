package com.thefirstrow.dreammate.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"sequence\"")
public class SequenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "registered_at")
    private Timestamp registeredAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "removed_at")
    private Timestamp removedAt;

    public static SequenceEntity of(String stageTitle, String stageContent, Long musicMs, String targetObject, Long effectNumber, String cameraName, Float x, Float y, Float z, UserEntity userEntity) {
        SequenceEntity sequenceEntity = new SequenceEntity();
        sequenceEntity.setStageTitle(stageTitle);
        sequenceEntity.setStageContent(stageContent);
        sequenceEntity.setMusicMs(musicMs);
        sequenceEntity.setTargetObject(targetObject);
        sequenceEntity.setEffectNumber(effectNumber);
        sequenceEntity.setCameraName(cameraName);
        sequenceEntity.setX(x);
        sequenceEntity.setY(y);
        sequenceEntity.setZ(z);
        sequenceEntity.setUser(userEntity);
        return sequenceEntity;
    }


    @PrePersist
    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

}
