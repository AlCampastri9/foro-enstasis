package com.foro.foro.web;

import com.foro.foro.domain.topic.dto.*;
import com.foro.foro.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    private final TopicService service;

    public TopicController(TopicService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<java.util.List<TopicResponseDTO>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> detalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponseDTO> crear(@RequestBody @Valid TopicCreateDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicResponseDTO> actualizar(@PathVariable Long id, @RequestBody @Valid TopicUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
