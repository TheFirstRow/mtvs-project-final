package com.thefirstrow.dreammate.model.entity;

import com.thefirstrow.dreammate.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "\"avatar\"")
@NoArgsConstructor
public class AvatarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gender;
    private String top;
    private String bottom;
    private String shoes;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "registered_at")
    private Timestamp registeredAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "removed_at")
    private Timestamp removedAt;


    @PrePersist
    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public static AvatarEntity of(String gender, String top, String bottom, String shoes, UserEntity user) {
        AvatarEntity entity = new AvatarEntity();
        entity.setGender(gender);
        entity.setTop(top);
        entity.setBottom(bottom);
        entity.setShoes(shoes);
        entity.setUser(user);
        return entity;
    }
}
