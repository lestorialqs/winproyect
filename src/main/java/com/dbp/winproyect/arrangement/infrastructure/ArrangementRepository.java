package com.dbp.winproyect.arrangement.infrastructure;


import com.dbp.winproyect.arrangement.domain.Arrangement;
import com.dbp.winproyect.client.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArrangementRepository extends JpaRepository<Arrangement, Long>{

    Optional<Arrangement> findByIdAndClient(Long id, Client client);

    List<Arrangement> findAllByClient_Id(Long clientId);

}
