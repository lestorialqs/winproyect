package com.dbp.winproyect.serviceEntity.infrastructure;

import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;


@Repository
public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, Long> {


    Page<ServiceEntity> findAll(Pageable pageable);
}
