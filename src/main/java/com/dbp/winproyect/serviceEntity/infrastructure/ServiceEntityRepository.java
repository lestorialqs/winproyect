package com.dbp.winproyect.serviceEntity.infrastructure;

import com.dbp.winproyect.review.domain.Review;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.serviceEntity.dto.ServiceDtoResponse;
import com.dbp.winproyect.tag.domain.ServiceTag;
import com.dbp.winproyect.tag.domain.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;


@Repository
public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, Long> {

    List<ServiceEntity> findAllByTags_Name(ServiceTag tagName);

}
