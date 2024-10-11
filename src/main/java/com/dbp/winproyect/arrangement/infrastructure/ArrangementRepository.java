package com.dbp.winproyect.arrangement.infrastructure;


import com.dbp.winproyect.arrangement.domain.Arrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrangementRepository extends JpaRepository<Arrangement, Long>{

}
