package com.dbp.winproyect.appuser.infrastructure;


import com.dbp.winproyect.appuser.domain.AppUser;
import com.dbp.winproyect.provider.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface BaseAppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    Optional<Provider> getByEmail(String mail);
}