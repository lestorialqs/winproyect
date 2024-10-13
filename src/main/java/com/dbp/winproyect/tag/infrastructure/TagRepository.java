package com.dbp.winproyect.tag.infrastructure;


import com.dbp.winproyect.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Set<Tag> findAllByServicesId(Long serviceId);
//    List<Tag> findAllByTags(Long serviceId);
}
