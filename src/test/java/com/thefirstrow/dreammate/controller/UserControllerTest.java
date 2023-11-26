package com.thefirstrow.dreammate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thefirstrow.dreammate.controller.request.UserJoinRequest;
import com.thefirstrow.dreammate.controller.request.UserLoginRequest;
import com.thefirstrow.dreammate.exception.DreamMateApplicationException;
import com.thefirstrow.dreammate.exception.ErrorCode;
import com.thefirstrow.dreammate.model.User;
import com.thefirstrow.dreammate.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithAnonymousUser
    public void 회원가입() throws Exception {
        String email = "email@email.com";
        String nickname = "nickname";
        String password = "password";
        String confirmPassword = "password";

        when(userService.join(email, nickname, password, confirmPassword)).thenReturn(mock(User.class));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest("email@email,com", "nickname", "password", "password"))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void 회원가입시_같은_아이디로_회원가입하면_에러발생() throws Exception {
        String email = "email@email.com";
        String nickname = "nickname";
        String password = "password";
        String confirmPassword = "password";

        when(userService.join(email, nickname, password, confirmPassword)).thenThrow(new DreamMateApplicationException(ErrorCode.DUPLICATED_USER_EMAIL));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest("email@email.com", "nickname", "password", "password"))))
                .andDo(print())
                .andExpect(status().is(ErrorCode.DUPLICATED_USER_EMAIL.getStatus().value()));
    }

    @Test
    @WithAnonymousUser
    public void 로그인() throws Exception {
        String email = "email@email.com";
        String password = "password";

        when(userService.login(email, password)).thenReturn("testToken");

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest("email", "password"))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void 로그인시_회원가입한적이_없다면_에러발생() throws Exception {
        String email = "email@email.com";
        String password = "password";

        when(userService.login(email, password)).thenThrow(new DreamMateApplicationException(ErrorCode.USER_NOT_FOUND));

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest("email@email.com", "password"))))
                .andDo(print())
                .andExpect(status().is(ErrorCode.USER_NOT_FOUND.getStatus().value()));
    }

    @Test
    @WithAnonymousUser
    public void 로그인시_비밀번호가_다르면_에러발생() throws Exception {
        String email = "email@email.com";
        String password = "password";

        when(userService.login(email, password)).thenThrow(new DreamMateApplicationException(ErrorCode.INVALID_PASSWORD));

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest("email@email.com", "password"))))
                .andDo(print())
                .andExpect(status().is(ErrorCode.INVALID_PASSWORD.getStatus().value()));
    }


}
