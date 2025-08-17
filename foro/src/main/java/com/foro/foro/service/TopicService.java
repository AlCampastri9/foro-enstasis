package com.foro.foro.service;

import com.foro.foro.domain.course.Course;
import com.foro.foro.domain.course.CourseRepository;
import com.foro.foro.domain.topic.*;
import com.foro.foro.domain.topic.dto.*;
import com.foro.foro.domain.user.User;
import com.foro.foro.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public TopicService(TopicRepository topicRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public TopicResponseDTO create(TopicCreateDTO dto) {
        if (topicRepository.existsByTitleAndMessage(dto.title(), dto.message())) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje");
        }
        User author = userRepository.findById(dto.authorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
        Course course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));

        Topic topic = Topic.builder()
                .title(dto.title())
                .message(dto.message())
                .author(author)
                .course(course)
                .build();

        Topic saved = topicRepository.save(topic);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public java.util.List<TopicResponseDTO> findAll() {
        return topicRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public TopicResponseDTO findById(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));
        return toResponse(topic);
    }

    @Transactional
    public TopicResponseDTO update(Long id, TopicUpdateDTO dto) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        if (!(dto.title().equals(topic.getTitle()) && dto.message().equals(topic.getMessage()))
                && topicRepository.existsByTitleAndMessage(dto.title(), dto.message())) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje");
        }

        topic.setTitle(dto.title());
        topic.setMessage(dto.message());
        return toResponse(topic);
    }

    @Transactional
    public void delete(Long id) {
        if (!topicRepository.existsById(id)) {
            throw new EntityNotFoundException("Tópico no encontrado");
        }
        topicRepository.deleteById(id);
    }

    private TopicResponseDTO toResponse(Topic t) {
        return new TopicResponseDTO(
                t.getId(),
                t.getTitle(),
                t.getMessage(),
                t.getCreatedAt(),
                t.getStatus(),
                t.getAuthor().getUsername(),
                t.getCourse().getName()
        );
    }
}
