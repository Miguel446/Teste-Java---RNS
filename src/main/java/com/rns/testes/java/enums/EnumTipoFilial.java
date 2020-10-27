package com.rns.testes.java.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import lombok.Getter;

@Getter
public enum EnumTipoFilial {

	DEPOSITO("Depósito"), LOJA_PF("Loja - BtoC"), LOJA_PJ("Loja - BtoB");

	private String descricao;

	EnumTipoFilial(String descricao) {
		this.descricao = descricao;
	}

	public static List<EnumTipoFilial> getAll() {
		return new ArrayList<>(EnumSet.allOf(EnumTipoFilial.class));
	}
}
