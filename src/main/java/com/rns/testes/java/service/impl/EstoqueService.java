package com.rns.testes.java.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rns.testes.java.dao.IEstoqueDao;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Movimentacao;
import com.rns.testes.java.service.AbstractGenericServicePersistence;
import com.rns.testes.java.service.IEstoqueService;
import com.rns.testes.java.service.IFilialService;

@Service
public class EstoqueService extends AbstractGenericServicePersistence<IEstoqueDao, Estoque, Long>
		implements IEstoqueService {

	@Autowired
	private IEstoqueDao estoqueDao;
	@Autowired
	private IFilialService filialService;

	@Override
	public Boolean isEstoqueDuplicado(Long filialId, String produtoId) {
		return estoqueDao.existsByFilial_IdEqualsAndProduto_IdEquals(filialId, produtoId);
	}

	public void transfer(Movimentacao movimentacao) {
		Optional<Estoque> optEstoqueRemetente = estoqueDao.findById(movimentacao.getEstoqueId());
		Long quantidade = movimentacao.getQuantidade();

		if (optEstoqueRemetente.isEmpty()) {
			throw new UnsupportedOperationException("Estoque remetente não encontrado");
		}

		Estoque estoqueRemetente = optEstoqueRemetente.get();
		if (quantidade > estoqueRemetente.getQuantidade()) {
			throw new UnsupportedOperationException(
					"A quantidade a transferir não pode ser maior que a quantidade em estoque");
		}
		estoqueRemetente.setQuantidade(estoqueRemetente.getQuantidade() - quantidade);
		estoqueDao.save(estoqueRemetente);

		Optional<Estoque> estoqueDestinatario = estoqueDao.findByFilial_IdEqualsAndProduto_IdEquals(
				movimentacao.getNovaFilialId(), estoqueRemetente.getProduto().getId());

		Estoque estoque = new Estoque();
		if (estoqueDestinatario.isEmpty()) {
			estoque.setProduto(estoqueRemetente.getProduto());
			estoque.setQuantidade(estoqueRemetente.getQuantidade());
			estoque.setFilial(filialService.findById(movimentacao.getNovaFilialId()));
			estoqueDao.save(estoque);
		} else {
			estoque = estoqueDestinatario.get();
			estoque.setQuantidade(estoque.getQuantidade() + quantidade);
			estoqueDao.save(estoque);
		}

	}

	public List<Estoque> findByFilial(Long filialId) {
		return estoqueDao.findAllByFilial_IdEquals(filialId);
	}

}
