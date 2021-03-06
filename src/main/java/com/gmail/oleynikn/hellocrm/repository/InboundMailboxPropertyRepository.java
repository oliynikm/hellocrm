package com.gmail.oleynikn.hellocrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.oleynikn.hellocrm.model.InboundMailboxProperty;

@Repository
public interface InboundMailboxPropertyRepository extends JpaRepository<InboundMailboxProperty, Long> {
}
