package org.jeansamuel.swagg.dao;

import org.jeansamuel.swagg.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
}
