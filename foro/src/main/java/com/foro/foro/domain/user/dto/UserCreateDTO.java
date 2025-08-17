package com.foro.foro.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO(
        @NotBlank String username,
        @NotBlank String password
) {}
