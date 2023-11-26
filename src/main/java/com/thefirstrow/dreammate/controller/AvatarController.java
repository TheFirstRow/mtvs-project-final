package com.thefirstrow.dreammate.controller;

import com.thefirstrow.dreammate.controller.request.AvatarCreateRequest;
import com.thefirstrow.dreammate.controller.request.AvatarUpdateRequest;
import com.thefirstrow.dreammate.controller.response.MyAvatarResponse;
import com.thefirstrow.dreammate.controller.response.Response;
import com.thefirstrow.dreammate.model.User;
import com.thefirstrow.dreammate.service.AvatarService;
import com.thefirstrow.dreammate.utils.ClassUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/avatar")
@AllArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;

    @PostMapping
    public Response<Long> create(@RequestBody AvatarCreateRequest request, Authentication authentication) {
        Long avatarId = avatarService.create(authentication.getName(), request.getGender(), request.getTop(), request.getBottom(), request.getShoes());
        return Response.success(avatarId);
    }


    @GetMapping("/{avatarId}")
    public Response<MyAvatarResponse> myAvatar(@PathVariable Long avatarId, Authentication authentication) {
        return Response.success(MyAvatarResponse.fromAvatar(avatarService.getMyAvatar(avatarId)));
    }

    @PutMapping("/{avatarId}")
    public Response<MyAvatarResponse> modify(@PathVariable Long avatarId, @RequestBody AvatarUpdateRequest request, Authentication authentication) {
        User user = ClassUtils.getSafeCastInstance(authentication.getPrincipal(), User.class);
        return Response.success(
                MyAvatarResponse.fromAvatar(
                        avatarService.modify(user.getId(), avatarId, request.getGender(), request.getTop(), request.getBottom(), request.getShoes())));
    }

}
