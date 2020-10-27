package com.rns.testes.java.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rns.testes.java.model.Movimentacao;

public interface IMovimentacaoDao extends JpaRepository<Movimentacao, Long> {

	List<Movimentacao> findAllByStatusTrue();

	Movimentacao findFirstByStatusTrueAndIdEquals(Long id);

	List<Movimentacao> findAllByStatusTrueAndDataHoraBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

}
