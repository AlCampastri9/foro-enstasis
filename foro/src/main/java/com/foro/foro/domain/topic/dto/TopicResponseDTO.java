package com.foro.foro.domain.topic.dto;

import com.foro.foro.domain.topic.TopicStatus;
import java.time.LocalDateTime;

public record TopicResponseDTO(
        Long id,
        String title,
        String message,
        LocalDateTime createdAt,
        TopicStatus status,
        String author,
        String course
) {}
