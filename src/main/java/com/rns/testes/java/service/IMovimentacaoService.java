package com.rns.testes.java.service;

import java.time.LocalDate;
import java.util.List;

import com.rns.testes.java.model.Movimentacao;

public interface IMovimentacaoService extends IGenericService<Movimentacao, Long> {

	public List<Movimentacao> findByDate(LocalDate date);

}
