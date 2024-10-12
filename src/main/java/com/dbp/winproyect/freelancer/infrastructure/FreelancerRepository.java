package com.dbp.winproyect.freelancer.infrastructure;


import com.dbp.winproyect.freelancer.domain.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer,Long> {

}
