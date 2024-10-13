package com.dbp.winproyect.arrangement.infrastructure;


import com.dbp.winproyect.arrangement.domain.Arrangement;
import com.dbp.winproyect.arrangement.domain.Status;
import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArrangementRepository extends JpaRepository<Arrangement, Long>{

    Optional<Arrangement> findByIdAndClient(Long id, Client client);

    Optional<List<Arrangement>> findAllByClient_Id(Long clientId);
    Optional<Arrangement> findByClient_Id(Long clientId);

    Optional<Arrangement> findByIdAndClient_Id(Long clientId, Long id);
    List<Arrangement> findAllByClientAndServiceEntityAndStatusNot(Client client, ServiceEntity serviceEntity, Status status);
    Optional<Arrangement> findByServiceEntity_IdAndClient_IdAndStatus( Long serviceEntityId, Long clientId, Status status);


}
