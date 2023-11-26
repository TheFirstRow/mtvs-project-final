package com.thefirstrow.dreammate.model;

import com.thefirstrow.dreammate.model.entity.AvatarEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avatar {

    private Long id;
    private String gender;
    private String top;
    private String bottom;
    private String shoes;
    private User user;
    private Timestamp registeredAt;
    private Timestamp updatedAt;
    private Timestamp removedAt;

    public static Avatar fromEntity(AvatarEntity entity) {
        return new Avatar(
                entity.getId(),
                entity.getGender(),
                entity.getTop(),
                entity.getBottom(),
                entity.getShoes(),
                User.fromEntity(entity.getUser()),
                entity.getRegisteredAt(),
                entity.getUpdatedAt(),
                entity.getRemovedAt()
        );
    }
}
