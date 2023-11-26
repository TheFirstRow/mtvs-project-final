package com.thefirstrow.dreammate.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not founded"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid password"),
    DUPLICATED_USER_EMAIL(HttpStatus.CONFLICT, "Duplicated user email"),
    DUPLICATED_USER_NICKNAME(HttpStatus.CONFLICT, "Duplicated user nickname"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "User has invalid permission"),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurs"),
    NOTIFICATION_CONNECT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Connect to notification occurs error"),
    CONFIRM_PASSWORD_NOT_MATCH(HttpStatus.CONFLICT, "The passwords do not match."),
    AVATAR_NOT_FOUND(HttpStatus.NOT_FOUND, "Avatar not founded"),
    AVATAR_ALREADY_EXIST(HttpStatus.CONFLICT, "User already has an avatar"),
    SEQUENCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Sequence not founded")
    ;

    private final HttpStatus status;
    private final String message;
}
