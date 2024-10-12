package com.dbp.winproyect.enterprise.infrastructure;

import com.dbp.winproyect.enterprise.domain.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
}
