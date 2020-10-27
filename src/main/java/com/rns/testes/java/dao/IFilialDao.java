package com.rns.testes.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rns.testes.java.model.Filial;

/**
 * Interface representa a camada de persistência da entidade Filial. Deve ser
 * injetada <b>exclusivamente</b> em uma camada service.
 */
public interface IFilialDao extends JpaRepository<Filial, Long> {

}
