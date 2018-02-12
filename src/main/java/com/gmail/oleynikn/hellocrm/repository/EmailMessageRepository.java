package com.gmail.oleynikn.hellocrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.oleynikn.hellocrm.model.EmailMessage;


@Repository
public interface EmailMessageRepository extends JpaRepository<EmailMessage, Long> {

    List<EmailMessage> findByClientId(Long id);

    Long deleteByClientId(Long id);

}
