package org.example.s31722tpo10.Controller;

import org.example.s31722tpo10.DataLayer.Models.LinkDTO;
import org.example.s31722tpo10.Utils.DeleteRes;
import org.example.s31722tpo10.Service.LinkService;
import org.example.s31722tpo10.Utils.ErrorMapper;
import org.example.s31722tpo10.Utils.UpdateRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/links")
public class LinkController {
    private final LinkService service;

    public LinkController(LinkService service) {

        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LinkDTO> create(@RequestBody LinkDTO dto) {
        LinkDTO saved = service.create(dto);

        var location = URI.create(saved.getInfoPageUrl());
        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkDTO> get(@PathVariable String id) {
        return service.get(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(@PathVariable String id,
                                      @RequestBody LinkDTO dto) {
        UpdateRes res = service.update(id, dto, dto.getPassword());
        return ErrorMapper.map(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id,
                                       @RequestHeader(value = "pass", required = false) String pass) {
        DeleteRes res = service.delete(id, pass);
        return ErrorMapper.map(res);
    }
}
