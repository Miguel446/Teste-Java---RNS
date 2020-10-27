package com.rns.testes.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rns.testes.java.model.Produto;

/**
 * Interface representa a camada de persistência da entidade Produto. Deve ser
 * injetada <b>exclusivamente</b> em uma camada service.
 */
public interface IProdutoDao extends JpaRepository<Produto, String> {

}
