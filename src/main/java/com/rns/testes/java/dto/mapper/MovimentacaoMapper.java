package com.rns.testes.java.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.rns.testes.java.dto.MovimentacaoDto;
import com.rns.testes.java.model.Movimentacao;

@Mapper
public interface MovimentacaoMapper extends GenericMapper<Movimentacao, MovimentacaoDto> {

	MovimentacaoMapper INSTANCE = Mappers.getMapper(MovimentacaoMapper.class);

}
