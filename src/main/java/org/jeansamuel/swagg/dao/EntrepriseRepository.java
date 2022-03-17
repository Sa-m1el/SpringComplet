package org.jeansamuel.swagg.dao;


import org.jeansamuel.swagg.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
}
