package com.dbp.winproyect.provider.infrastructure;

import com.dbp.winproyect.provider.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
