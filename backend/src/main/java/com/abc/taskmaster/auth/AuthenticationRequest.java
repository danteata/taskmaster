package com.abc.taskmaster.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}