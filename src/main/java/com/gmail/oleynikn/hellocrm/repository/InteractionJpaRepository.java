package com.gmail.oleynikn.hellocrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.oleynikn.hellocrm.model.Interaction;


@Repository
public interface InteractionJpaRepository extends JpaRepository<Interaction, Long> {

    List<Interaction> findByClientId(Long id);

    Long deleteByClientId(Long id);

}
