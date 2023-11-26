package com.thefirstrow.dreammate.controller;

import com.thefirstrow.dreammate.controller.request.SequenceCreateRequest;
import com.thefirstrow.dreammate.controller.request.SequenceUpdateRequest;
import com.thefirstrow.dreammate.controller.response.MyAvatarResponse;
import com.thefirstrow.dreammate.controller.response.MySequenceResponse;
import com.thefirstrow.dreammate.controller.response.Response;
import com.thefirstrow.dreammate.model.entity.SequenceEntity;
import com.thefirstrow.dreammate.model.entity.UserEntity;
import com.thefirstrow.dreammate.service.SequenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sequences")
@AllArgsConstructor
public class SequenceController {

    private final SequenceService sequenceService;

    @PostMapping
    public Response<Long> createSequence(@RequestBody SequenceCreateRequest request, Authentication authentication) {
        Long sequenceId = sequenceService.createSequence(authentication.getName(), request.getStageTitle(), request.getStageContent(),
                request.getMusicMs(), request.getTargetObject(), request.getEffectNumber(), request.getCameraName(),
                request.getX(), request.getY(), request.getZ());
        return Response.success(sequenceId);
    }

    // 내가 만든 시퀀스 목록 조회
//    @GetMapping("/mySequences")
//    public List<SequenceEntity> getMySequences(Authentication authentication) {
//        return sequenceService.getMySequences(authentication.getName());
//    }
//
//    // 내가 만든 시퀀스를 제외한 모든 시퀀스 조회
//    @GetMapping("/otherUsersSequences")
//    public List<SequenceEntity> getOtherUsersSequences(Authentication authentication) {
//        return sequenceService.getOtherUsersSequences(authentication.getName());
//    }


//    @GetMapping("/{sequenceId}")
//    public Response<MyAvatarResponse> SequenceDetail(@PathVariable Long sequenceId, Authentication authentication) {
//        return Response.success(MySequenceResponse.fromSequence(sequenceService.getMySequences(sequenceId)));
//    }

    @PutMapping("/{sequenceId}")
    public Response<SequenceEntity> updateSequence(@PathVariable Long sequenceId,
                                                         @RequestBody SequenceUpdateRequest request,
                                                         Authentication authentication) {
        SequenceEntity updatedSequence = sequenceService.updateSequence(authentication.getName(), sequenceId, request.getStageTitle(),
                request.getStageContent(), request.getMusicMs(), request.getTargetObject(), request.getEffectNumber(),
                request.getCameraName(), request.getX(), request.getY(), request.getZ());
        return Response.success(updatedSequence);
    }

//    // 시퀀스 삭제
//    @DeleteMapping("/{sequenceId}")
//    public ResponseEntity<Void> deleteSequence(@PathVariable Long sequenceId) {
//        sequenceService.deleteSequence(sequenceId);
//        return ResponseEntity.ok().build();
//    }

    // 시퀀스 복사 및 저장(잘모르게뜸...)
//    @PostMapping("/copy/{sequenceId}")
//    public ResponseEntity<Long> copyAndSaveSequence(@AuthenticationPrincipal UserEntity currentUser,
//                                                    @PathVariable Long sequenceId,
//                                                    @RequestBody SequenceCopyRequest request) {
//        Long newSequenceId = sequenceService.copyAndSaveSequence(currentUser.getId(), sequenceId, request.getNewStageTitle());
//        return ResponseEntity.ok(newSequenceId);
//    }
}
