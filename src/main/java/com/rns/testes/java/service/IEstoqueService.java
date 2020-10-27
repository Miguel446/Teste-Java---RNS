package com.rns.testes.java.service;

import java.util.List;

import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Movimentacao;

public interface IEstoqueService extends IGenericService<Estoque, Long> {

	Boolean isEstoqueDuplicado(Long filialId, String produtoId);

	void transfer(Movimentacao movimentacao);

	List<Estoque> findByFilial(Long filialId);

}
