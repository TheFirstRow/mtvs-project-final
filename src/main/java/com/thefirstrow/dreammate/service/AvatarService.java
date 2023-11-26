package com.thefirstrow.dreammate.service;

import com.thefirstrow.dreammate.controller.response.MyAvatarResponse;
import com.thefirstrow.dreammate.exception.DreamMateApplicationException;
import com.thefirstrow.dreammate.exception.ErrorCode;
import com.thefirstrow.dreammate.model.Avatar;
import com.thefirstrow.dreammate.model.entity.AvatarEntity;
import com.thefirstrow.dreammate.model.entity.UserEntity;
import com.thefirstrow.dreammate.repository.AvatarEntityRepository;
import com.thefirstrow.dreammate.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvatarService {

    private final UserEntityRepository userEntityRepository;
    private final AvatarEntityRepository avatarEntityRepository;

    @Transactional
    public Long create(String userEmail, String gender, String top, String bottom, String shoes) {

        UserEntity userEntity = userEntityRepository.findByEmail(userEmail)
                .orElseThrow(() -> new DreamMateApplicationException(ErrorCode.USER_NOT_FOUND, String.format("userEmail is %s", userEmail)));

        if (avatarEntityRepository.findByUser(userEntity).isPresent()) {
            throw new DreamMateApplicationException(ErrorCode.AVATAR_ALREADY_EXIST, String.format("User already has an avatar"));
        }

        AvatarEntity avatarEntity = AvatarEntity.of(gender, top, bottom, shoes, userEntity);
        AvatarEntity save = avatarEntityRepository.save(avatarEntity);

        return save.getId();
    }

    public Avatar getMyAvatar(Long avatarId) {
        AvatarEntity avatarEntity = avatarEntityRepository.findById(avatarId).orElseThrow(() -> new DreamMateApplicationException(ErrorCode.AVATAR_NOT_FOUND, String.format("avatarId is %d", avatarId)));
        return Avatar.fromEntity(avatarEntity);
    }

    @Transactional
    public Avatar modify(Long userId, Long avatarId, String gender, String top, String bottom, String shoes) {
        AvatarEntity avatarEntity = avatarEntityRepository.findById(avatarId).orElseThrow(() -> new DreamMateApplicationException(ErrorCode.AVATAR_NOT_FOUND, String.format("avatarId is %d", avatarId)));
        if (!Objects.equals(avatarEntity.getUser().getId(), userId)) {
            throw new DreamMateApplicationException(ErrorCode.INVALID_PERMISSION, String.format("user %s has no permission with avatar %d", userId, avatarId));
        }

        avatarEntity.setGender(gender);
        avatarEntity.setTop(top);
        avatarEntity.setBottom(bottom);
        avatarEntity.setShoes(shoes);

        return Avatar.fromEntity(avatarEntityRepository.saveAndFlush(avatarEntity));
    }

}
