package com.rns.testes.java.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class MovimentacaoDto {

	private Long id;
	@NotEmpty(message = "A data e hora não pode ser vazia")
	private String dataHora;
	@NotNull(message = "A quantidade não pode ser vazia")
	@Positive(message = "A quantidade não pode ser negativa ou igual a 0")
	private Long quantidade;

	@NotNull(message = "O id do estoque não pode ser vazio")
	private Long estoqueId;
	@NotNull(message = "O id da nova filial não pode ser vazio")
	private Long novaFilialId;

}
