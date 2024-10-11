package com.dbp.winproyect.appuser.infrastructure;


import com.dbp.winproyect.appuser.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseAppUserRepository<T extends AppUser> extends JpaRepository<T, Long>{
}
