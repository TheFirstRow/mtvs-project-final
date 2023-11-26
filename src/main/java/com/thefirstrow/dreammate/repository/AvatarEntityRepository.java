package com.thefirstrow.dreammate.repository;

import com.thefirstrow.dreammate.model.entity.AvatarEntity;
import com.thefirstrow.dreammate.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarEntityRepository extends JpaRepository<AvatarEntity, Long> {

    Optional<AvatarEntity> findById(Long id);
    Optional<AvatarEntity> findByUser(UserEntity user);

}
