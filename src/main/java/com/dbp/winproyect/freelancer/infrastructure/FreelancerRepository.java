package com.dbp.winproyect.freelancer.infrastructure;


import com.dbp.winproyect.freelancer.domain.Freelance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelancerRepository extends JpaRepository<Freelance,Long> {

}
