package com.foro.foro.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitleAndMessage(String title, String message);
    Optional<Topic> findByTitleAndMessage(String title, String message);
}
