package com.rns.testes.java.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class EstoqueDto {

	private Long id;
	@NotEmpty(message = "O produto n�o pode ser vazio")
	private String produtoId;
	@NotNull(message = "A filial n�o pode ser vazia")
	private Long filialId;
	@NotNull(message = "A quantidade n�o pode ser vazia")
	@Positive(message = "A quantidade n�o pode ser negativa ou igual a 0")
	private Long quantidade;
}
