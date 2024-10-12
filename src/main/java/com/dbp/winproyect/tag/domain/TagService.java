package com.dbp.winproyect.tag.domain;

import com.dbp.winproyect.tag.infrastructure.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TagService {


    private final TagRepository tagRepository;
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    public Set<Tag> findByServiceId(Long serviceId) {
        return tagRepository.findAllByServicesId(serviceId);
    }
}
