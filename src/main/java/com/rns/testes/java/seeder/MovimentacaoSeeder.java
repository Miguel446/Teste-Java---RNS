package com.rns.testes.java.seeder;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.rns.testes.java.model.Movimentacao;
import com.rns.testes.java.service.IMovimentacaoService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MovimentacaoSeeder {

	@Autowired
	private IMovimentacaoService service;

	@EventListener
	public void seedFilial(ContextRefreshedEvent event) {
		try {
			log.info("Criando movimentacoes....");
			criandoMovimentacoes();
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	// TODO corrigir dados de movimentacoes
	private void criandoMovimentacoes() {
		Movimentacao m1 = new Movimentacao();
		m1.setDataHora(LocalDateTime.of(2020, 10, 20, 10, 30));
		m1.setQuantidade(12L);
		service.save(m1);

		Movimentacao m2 = new Movimentacao();
		m2.setDataHora(LocalDateTime.of(2020, 10, 22, 9, 0));
		m2.setQuantidade(7L);
		service.save(m2);

		Movimentacao m3 = new Movimentacao();
		m3.setDataHora(LocalDateTime.of(2020, 10, 25, 14, 45));
		m3.setQuantidade(30L);
		service.save(m3);
	}

}
