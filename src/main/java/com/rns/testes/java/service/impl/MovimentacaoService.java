package com.rns.testes.java.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rns.testes.java.dao.IMovimentacaoDao;
import com.rns.testes.java.model.Movimentacao;
import com.rns.testes.java.service.AbstractGenericServicePersistence;
import com.rns.testes.java.service.IMovimentacaoService;

@Service
public class MovimentacaoService extends AbstractGenericServicePersistence<IMovimentacaoDao, Movimentacao, Long>
		implements IMovimentacaoService {

	@Autowired
	private IMovimentacaoDao movimentacaoDao;

	@Override
	public void delete(Movimentacao movimentacao) {
		movimentacao.setStatus(false);
		movimentacaoDao.save(movimentacao);
	}

	@Override
	public void delete(Long id) {
		Movimentacao movimentacao = movimentacaoDao.findFirstByStatusTrueAndIdEquals(id);
		movimentacao.setStatus(false);
		movimentacaoDao.save(movimentacao);
	}

	@Override
	public List<Movimentacao> findAll() {
		return movimentacaoDao.findAllByStatusTrue();
	}

	@Override
	public Movimentacao findById(Long id) {
		Optional<Movimentacao> m = Optional.ofNullable(movimentacaoDao.findFirstByStatusTrueAndIdEquals(id));
		return m.orElseThrow(() -> new UnsupportedOperationException("Objeto nao encontrado"));
	}

	public List<Movimentacao> findByDate(LocalDate date) {
		return movimentacaoDao.findAllByStatusTrueAndDataHoraBetween(date.atStartOfDay(), date.atTime(23, 59));
	}

}
