package com.rns.testes.java.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MOVIMENTACAO")
@Data
public class Movimentacao extends GenericEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	// Ao invés de deletar um valor da tabela, seu valor pode ser setado pra false
	// Isso garante que seja possível recuperar algum dado excluído indevidamente
	private Boolean status = true;

	@Column
	private Long estoqueId;

	@Column
	private Long novaFilialId;

	@Column
	private LocalDateTime dataHora;

	@Column
	private Long quantidade;

	@Column
	private String descricao;

}
