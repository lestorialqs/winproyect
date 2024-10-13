package com.dbp.winproyect.client.infrastructure;


import com.dbp.winproyect.appuser.domain.AppUser;
import com.dbp.winproyect.client.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<AppUser> findByEmail(String mail);

    //Optional<Client> updateClient(long l, Client updatedClientDetails);

    Optional<Client> findByDni(long l);
}
