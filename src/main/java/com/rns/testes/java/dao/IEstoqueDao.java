package com.rns.testes.java.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rns.testes.java.model.Estoque;

public interface IEstoqueDao extends JpaRepository<Estoque, Long> {

	Boolean existsByFilial_IdEqualsAndProduto_IdEquals(Long filialId, String produtoId);

	Optional<Estoque> findByFilial_IdEqualsAndProduto_IdEquals(Long filialId, String produtoId);

	List<Estoque> findAllByFilial_IdEquals(Long filialId);

}
