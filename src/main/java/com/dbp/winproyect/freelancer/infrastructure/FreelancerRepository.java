package com.dbp.winproyect.freelancer.infrastructure;


import com.dbp.winproyect.freelancer.domain.Freelancer;

import com.dbp.winproyect.provider.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FreelancerRepository extends JpaRepository<Freelancer,Long> {
    Optional<Freelancer> findByEmail(String email);
}
