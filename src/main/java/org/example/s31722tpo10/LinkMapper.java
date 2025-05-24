package org.example.s31722tpo10;

import org.example.s31722tpo10.DataLayer.Link;
import org.example.s31722tpo10.DataLayer.Models.LinkDTO;
import org.springframework.stereotype.Component;

@Component
public class LinkMapper {
    public LinkDTO toDto(Link entity, String baseURL) {
        if (entity == null) return null;
        LinkDTO dto = new LinkDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTargetUrl(entity.getTargetURL());
        dto.setRedirectUrl(baseURL + "/red/" + entity.getId());
        dto.setVisitsCount(entity.getVisitsCount());
        return dto;
    }

    public Link toEntity(LinkDTO dto) {
        if (dto == null) return null;
        Link entity = new Link();
        entity.setName(dto.getName());
        entity.setTargetURL(dto.getTargetUrl());
        return entity;
    }
}
