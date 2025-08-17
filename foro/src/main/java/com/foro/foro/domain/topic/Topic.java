package com.foro.foro.domain.topic;

import com.foro.foro.domain.course.Course;
import com.foro.foro.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "topics", uniqueConstraints = {
        @UniqueConstraint(name = "uq_topics_title_message", columnNames = {"title", "message"})
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TopicStatus status = TopicStatus.OPEN;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;
}
