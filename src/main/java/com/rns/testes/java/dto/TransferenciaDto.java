package com.rns.testes.java.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class TransferenciaDto {

	@NotNull(message = "O id do estoque não pode ser vazio")
	private Long estoqueId;
	@NotNull(message = "O id da nova filial não pode ser vazio")
	private Long novaFilialId;
	@NotNull(message = "A quantidade a transferir não pode ser vazio")
	@Positive(message = "A quantidade não pode ser negativa ou igual a 0")
	private Long qntATransferir;

}
