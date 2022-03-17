package org.jeansamuel.swagg.dao;

import org.jeansamuel.swagg.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
}
