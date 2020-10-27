package com.rns.testes.java.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class TransferenciaDto {

	@NotNull(message = "O id do estoque n�o pode ser vazio")
	private Long estoqueId;
	@NotNull(message = "O id da nova filial n�o pode ser vazio")
	private Long novaFilialId;
	@NotNull(message = "A quantidade a transferir n�o pode ser vazio")
	@Positive(message = "A quantidade n�o pode ser negativa ou igual a 0")
	private Long qntATransferir;

}
