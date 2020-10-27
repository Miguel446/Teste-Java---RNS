package com.rns.testes.java.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.rns.testes.java.dto.ProdutoDto;
import com.rns.testes.java.model.Produto;

/**
 * Interface responsável por implementar MapperStruct no mapeamento entre
 * Produto e ProdutoDto.
 */
@Mapper
public interface ProdutoMapper extends GenericMapper<Produto, ProdutoDto> {

	ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

}
