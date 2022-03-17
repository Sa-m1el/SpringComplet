package org.jeansamuel.swagg.dao;

import org.jeansamuel.swagg.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.DoubleStream;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

    Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);
}
