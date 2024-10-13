package com.dbp.winproyect.repostitorytests;

import com.dbp.winproyect.enterprise.domain.Enterprise;
import com.dbp.winproyect.provider.domain.Provider;
import java.util.Optional;

//import org.apache.el.stream.Optional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findByEmail(String email);

    Optional<Enterprise> findByRuc(long l);
}
