package org.jeansamuel.swagg.dao;

import org.jeansamuel.swagg.model.Client;
import org.jeansamuel.swagg.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {

    Optional<CommandeClient> findCommandeClientByCode(String code);
}
