package com.thefirstrow.dreammate.repository;

import com.thefirstrow.dreammate.model.entity.SequenceEntity;
import com.thefirstrow.dreammate.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SequenceEntityRepository extends JpaRepository<SequenceEntity, Long> {

    //내가 만든 시퀀스 조회
    List<SequenceEntity> findByUser(UserEntity user);

    // 내가 만든 시퀀스를 제외한 모든 시퀀스 조회
    List<SequenceEntity> findAllByUserNot(UserEntity user);
}
