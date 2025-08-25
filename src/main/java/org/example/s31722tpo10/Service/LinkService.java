package org.example.s31722tpo10.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.example.s31722tpo10.DataLayer.Link;
import org.example.s31722tpo10.DataLayer.Models.LinkDTO;
import org.example.s31722tpo10.Interfaces.LinkRepository;
import org.example.s31722tpo10.Utils.LinkMapper;
import org.example.s31722tpo10.Utils.DeleteRes;
import org.example.s31722tpo10.Utils.UpdateRes;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class LinkService {
    private final LinkRepository _linkRepository;
    private final LinkMapper _mapper;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private final Validator validator;

    public LinkService(LinkRepository _linkRepository, LinkMapper _mapper, Validator validator) {
        this._linkRepository = _linkRepository;
        this._mapper = _mapper;
        this.validator = validator;
    }

    private String generateRandomSlug() {
        return IntStream.range(0, 10)
                .mapToObj(i -> String
                        .valueOf(ALPHABET.charAt(
                                secureRandom.nextInt(
                                        ALPHABET.length()))))
                .collect(Collectors.joining());
    }

    public LinkDTO create(LinkDTO dto) {
        Set<ConstraintViolation<LinkDTO>> violations = validator.validate(dto);
        if(!violations.isEmpty()) throw new ConstraintViolationException(violations);
        Link entity = _mapper.toEntity(dto);
        entity.setId(generateRandomSlug());
        entity.setPasswordHash(dto.getPassword() == null ? null : passwordEncoder.encode(dto.getPassword()));
        _linkRepository.save(entity);
        return _mapper.toDto(entity);
    }

    public Optional<LinkDTO> get(String id) {
        return _linkRepository
                .findById(id)
                .map(_mapper::toDto);
    }

    public UpdateRes update(String id, LinkDTO patch, String password) {
        return _linkRepository.findById(id)
                .map(link -> {
                    if (passwordOk(link, password)) return UpdateRes.WRONG_PASSWORD;
                    if (patch.getName() != null) link.setName(patch.getName());
                    if (patch.getTargetUrl() != null) link.setTargetURL(patch.getTargetUrl());
                    _linkRepository.save(link);
                    return UpdateRes.OK;
                })
                .orElse(UpdateRes.NOT_FOUND);
    }

    private boolean passwordOk(Link link, String password) {
        if (!link.hasPassword()) return false;
        return password == null || !passwordEncoder.matches(password, link.getPasswordHash());
    }

    public DeleteRes delete(String id, String password) {
        return _linkRepository.findById(id).map(link -> {
            if (passwordOk(link, password)) return DeleteRes.WRONG_PASSWORD;
            _linkRepository.delete(link);
            return DeleteRes.Ok;
        }).orElse(DeleteRes.NOT_FOUND);
    }

    public URI resolveAndCountVisits(String id) {
        Link link = _linkRepository.findById(id).orElseThrow();
        link.setVisitsCount(link.getVisitsCount() + 1);
        _linkRepository.save(link);
        return URI.create(link.getTargetURL());
    }

}
