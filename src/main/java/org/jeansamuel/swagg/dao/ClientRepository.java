package org.jeansamuel.swagg.dao;


import org.jeansamuel.swagg.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
