package com.thefirstrow.dreammate.service;

import com.thefirstrow.dreammate.exception.DreamMateApplicationException;
import com.thefirstrow.dreammate.exception.ErrorCode;
import com.thefirstrow.dreammate.model.Avatar;
import com.thefirstrow.dreammate.model.Sequence;
import com.thefirstrow.dreammate.model.entity.AvatarEntity;
import com.thefirstrow.dreammate.model.entity.SequenceEntity;
import com.thefirstrow.dreammate.model.entity.UserEntity;
import com.thefirstrow.dreammate.repository.SequenceEntityRepository;
import com.thefirstrow.dreammate.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SequenceService {

    private final UserEntityRepository userEntityRepository;
    private final SequenceEntityRepository sequenceEntityRepository;

    // 시퀀스 생성
    @Transactional
    public Long createSequence(String userEmail, String stageTitle, String stageContent, Long musicMs, String targetObject, Long effectNumber, String cameraName, Float x, Float y, Float z) {

        UserEntity userEntity = userEntityRepository.findByEmail(userEmail)
                .orElseThrow(() -> new DreamMateApplicationException(ErrorCode.USER_NOT_FOUND, String.format("userEmail is %s", userEmail)));

        SequenceEntity sequenceEntity = SequenceEntity.of(stageTitle, stageContent, musicMs, targetObject, effectNumber, cameraName, x, y, z, userEntity);
        SequenceEntity save = sequenceEntityRepository.save(sequenceEntity);

        return save.getId();
    }

    // 시퀀스 조회
//    public Sequence getMySequences(Long sequenceId) {
//        AvatarEntity avatarEntity = avatarEntityRepository.findById(avatarId).orElseThrow(() -> new DreamMateApplicationException(ErrorCode.AVATAR_NOT_FOUND, String.format("avatarId is %d", avatarId)));
//        return Avatar.fromEntity(avatarEntity);
//
//
//        UserEntity userEntity = userEntityRepository.findById(userId)
//                .orElseThrow(() -> new DreamMateApplicationException(ErrorCode.USER_NOT_FOUND, String.format("userId is %s", userId)));
//        return sequenceEntityRepository.findByUser(userEntity);
//    }

    // 내가 만든 시퀀스를 제외한 모든 시퀀스 조회
    public List<SequenceEntity> getOtherUsersSequences(Long userId) {
        UserEntity userEntity = userEntityRepository.findById(userId)
                .orElseThrow(() -> new DreamMateApplicationException(ErrorCode.USER_NOT_FOUND, String.format("userId is %s", userId)));
        return sequenceEntityRepository.findAllByUserNot(userEntity);
    }

    // 시퀀스 수정
    @Transactional
    public SequenceEntity updateSequence(String email, Long sequenceId, String stageTitle, String stageContent, Long musicMs, String targetObject, Long effectNumber, String cameraName, Float x, Float y, Float z) {
        SequenceEntity sequenceEntity = sequenceEntityRepository.findById(sequenceId).orElseThrow(() -> new DreamMateApplicationException(ErrorCode.SEQUENCE_NOT_FOUND, String.format("sequenceId is %d", sequenceId)));
        if (!Objects.equals(sequenceEntity.getUser().getEmail(), email)) {
            throw new DreamMateApplicationException(ErrorCode.INVALID_PERMISSION, String.format("user %s has no permission with sequence %d", email, sequenceId));
        }

        sequenceEntity.setStageTitle(stageTitle);
        sequenceEntity.setStageContent(stageContent);
        sequenceEntity.setMusicMs(musicMs);
        sequenceEntity.setTargetObject(targetObject);
        sequenceEntity.setEffectNumber(effectNumber);
        sequenceEntity.setCameraName(cameraName);
        sequenceEntity.setX(x);
        sequenceEntity.setY(y);
        sequenceEntity.setZ(z);

        return sequenceEntityRepository.saveAndFlush(sequenceEntity);
    }

//    // 시퀀스 삭제
//    @Transactional
//    public void deleteSequence(Long sequenceId) {
//        sequenceEntityRepository.deleteById(sequenceId);
//    }
//
//    // 시퀀스 복사 및 저장
//    @Transactional
//    public Long copyAndSaveSequence(Long userId, Long sequenceId, String newStageTitle) {
//        SequenceEntity originalSequence = sequenceEntityRepository.findById(sequenceId).orElseThrow(() -> new DreamMateApplicationException(ErrorCode.SEQUENCE_NOT_FOUND, String.format("sequenceId is %d", sequenceId)));
//        SequenceEntity newSequence = new SequenceEntity();
//        newSequence.setStageTitle(newStageTitle);
//        newSequence.setStageContent(originalSequence.getStageContent());
//        newSequence.setMusicMs(originalSequence.getMusicMs());
//        newSequence.setTargetObject(originalSequence.getTargetObject());
//        newSequence.setEffectNumber(originalSequence.getEffectNumber());
//        newSequence.setCameraName(originalSequence.getCameraName());
//        newSequence.setX(originalSequence.getX());
//        newSequence.setY(originalSequence.getY());
//        newSequence.setZ(originalSequence.getZ());
//
//        UserEntity userEntity = userEntityRepository.findById(userId)
//                .orElseThrow(() -> new DreamMateApplicationException(ErrorCode.USER_NOT_FOUND, String.format("userId is %s", userId)));
//        newSequence.setUser(userEntity);
//
//        SequenceEntity savedSequence = sequenceEntityRepository.save(newSequence);
//
//        return savedSequence.getId();
//    }
}
