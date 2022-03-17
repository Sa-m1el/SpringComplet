package org.jeansamuel.swagg.dao;

import org.jeansamuel.swagg.model.LigneVente;
import org.jeansamuel.swagg.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MvtStkRepository extends JpaRepository<MvtStk, Integer> {
}
