package com.foro.foro.domain.topic.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicUpdateDTO(
        @NotBlank String title,
        @NotBlank String message
) {}
