package com.rns.testes.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ESTOQUE")
@SequenceGenerator(name = "ESTOQUE_SEQ", sequenceName = "ESTOQUE_SEQ", allocationSize = 1)
@Data
public class Estoque extends GenericEntity<Long> {

	@Id
	@GeneratedValue(generator = "ESTOQUQ_SEQ", strategy = GenerationType.SEQUENCE)
	private Long id;

	@OneToOne
	private Filial filial;

	@OneToOne
	private Produto produto;

	@Column
	private Long quantidade;

}
