package com.rns.testes.java.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rns.testes.java.dto.EstoqueDto;
import com.rns.testes.java.dto.MovimentacaoDto;
import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.Movimentacao;
import com.rns.testes.java.response.Response;
import com.rns.testes.java.service.IEstoqueService;
import com.rns.testes.java.service.IFilialService;
import com.rns.testes.java.service.IMovimentacaoService;
import com.rns.testes.java.service.IProdutoService;

@CrossOrigin
@RestController
@RequestMapping
public class EstoqueController {

	private static final String BASE_URL = "estoque/";

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	@Autowired
	private IEstoqueService estoqueService;
	@Autowired
	private IProdutoService produtoService;
	@Autowired
	private IFilialService filialService;
	@Autowired
	private IMovimentacaoService movimentacaoService;

	@GetMapping(value = BASE_URL + "find-all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Estoque>> findAll() {
		return ResponseEntity.ok(estoqueService.findAll());
	}

	@GetMapping(value = BASE_URL + "find-by-id", produces = MediaType.APPLICATION_JSON_VALUE, params = { "id" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Estoque> findById(@RequestParam(name = "id") Long id) {
		return ResponseEntity.ok(estoqueService.findById(id));
	}

	@GetMapping(value = BASE_URL + "find-by-filial", produces = MediaType.APPLICATION_JSON_VALUE, params = { "id" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Estoque>> findByFilial(@RequestParam(name = "id") Long id) {
		return ResponseEntity.ok(estoqueService.findByFilial(id));
	}

	@PostMapping(value = BASE_URL + "insert", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Response<Estoque>> save(@Valid @RequestBody EstoqueDto dto, BindingResult result) {
		Response<Estoque> response = new Response<Estoque>();
		validarEstoque(dto, result);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Estoque estoque = converterDtoParaEntidade(dto);
		estoqueService.save(estoque);
		response.setData(estoque);
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = BASE_URL + "update", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Response<Estoque>> update(@Valid @RequestBody EstoqueDto dto, BindingResult result) {
		Response<Estoque> response = new Response<Estoque>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Estoque estoque = converterDtoParaEntidade(dto);
		estoqueService.update(estoque);
		response.setData(estoque);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = BASE_URL + "delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@RequestParam(name = "id") Long id) {
		estoqueService.delete(id);
	}

	@PostMapping(value = BASE_URL + "transfer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Response<Movimentacao>> transfer(@Valid @RequestBody MovimentacaoDto dto,
			BindingResult result) {
		Response<Movimentacao> response = new Response<Movimentacao>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Movimentacao movimentacao = converterDtoParaEntidade(dto);
		estoqueService.transfer(movimentacao);
		movimentacaoService.save(movimentacao);
		response.setData(movimentacao);
		return ResponseEntity.ok(response);
	}

	private Estoque converterDtoParaEntidade(EstoqueDto dto) {
		Estoque e = new Estoque();
		e.setId(dto.getId());
		e.setFilial(filialService.findById(dto.getFilialId()));
		e.setProduto(produtoService.findById(dto.getProdutoId()));
		e.setQuantidade(dto.getQuantidade());
		return e;
	}

	private Movimentacao converterDtoParaEntidade(MovimentacaoDto dto) {
		Movimentacao m = new Movimentacao();
		m.setDataHora(LocalDateTime.parse(dto.getDataHora(), formatter));
		m.setEstoqueId(dto.getEstoqueId());
		m.setNovaFilialId(dto.getNovaFilialId());
		m.setQuantidade(dto.getQuantidade());
		m.setDescricao(dto.getDescricao());
		return m;
	}

	private void validarEstoque(EstoqueDto dto, BindingResult result) {
		if (estoqueService.isEstoqueDuplicado(dto.getFilialId(), dto.getProdutoId())) {
			result.addError(new ObjectError("estoque",
					"Uma filial não pode ter o mesmo produto cadastrado mais de uma vez no estoque"));
		}

	}

}
