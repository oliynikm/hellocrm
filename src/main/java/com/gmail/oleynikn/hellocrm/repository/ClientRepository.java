package com.gmail.oleynikn.hellocrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.oleynikn.hellocrm.model.Client;
import java.lang.String;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByFirstNameOrLastNameAllIgnoreCase(String firstName, String lastName);

    List<Client> findByEmail(String address);

}
