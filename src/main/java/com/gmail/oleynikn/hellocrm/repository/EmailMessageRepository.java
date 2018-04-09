package com.gmail.oleynikn.hellocrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gmail.oleynikn.hellocrm.model.Client;
import com.gmail.oleynikn.hellocrm.model.EmailMessage;


@Repository
public interface EmailMessageRepository extends JpaRepository<EmailMessage, Long> {

    List<EmailMessage> findByClientId(Long id);

    List<EmailMessage> findByState(String state);

    Long deleteByClientId(Long id);

    Long countByClientId(Long id);

    Long countByState(String state);

    Long countByMessageId(String messageId);

    List<EmailMessage> getByClientAddress(String clientAddress);

    @Modifying
    @Query("Update EmailMessage e set e.client=?1 where e.clientAddress=?2")
    int setClientForAddress(Client client, String address);

}
