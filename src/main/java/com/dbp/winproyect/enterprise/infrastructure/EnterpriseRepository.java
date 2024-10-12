package com.dbp.winproyect.enterprise.infrastructure;

import com.dbp.winproyect.enterprise.domain.Enterprise;
import com.dbp.winproyect.provider.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Optional<Enterprise> findByEmail(String email);
}
