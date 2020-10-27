package com.rns.testes.java.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rns.testes.java.dto.MovimentacaoDto;
import com.rns.testes.java.model.Movimentacao;
import com.rns.testes.java.response.Response;
import com.rns.testes.java.service.IMovimentacaoService;

@CrossOrigin
@RestController
@RequestMapping
public class MovimentacaoController {

	private static final String BASE_URL = "movimentacao/";

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	@Autowired
	private IMovimentacaoService service;

	@GetMapping(value = BASE_URL + "find-all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Movimentacao>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = BASE_URL + "find-by-date", produces = MediaType.APPLICATION_JSON_VALUE, params = { "date" })
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Movimentacao>> findByDate(@RequestParam(name = "date") String dateStr) {
		LocalDate date = LocalDate.parse(dateStr);
		return ResponseEntity.ok(service.findByDate(date));
	}

	@PutMapping(value = BASE_URL + "update", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Response<Movimentacao>> update(@Valid @RequestBody MovimentacaoDto dto,
			BindingResult result) {
		Response<Movimentacao> response = new Response<Movimentacao>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Movimentacao movimentacao = converterDtoParaEntidade(dto);
		service.update(movimentacao);
		response.setData(movimentacao);
		return ResponseEntity.ok(response);
	}
	// O usuário nao pode inserir um log manualmente para não causar inconsistencias
	// no estoque, apenas atualiza-lo.

	@DeleteMapping(value = BASE_URL + "delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@RequestParam(name = "id") Long id) {
		service.delete(id);
	}

	private Movimentacao converterDtoParaEntidade(MovimentacaoDto dto) {
		Movimentacao m = new Movimentacao();
		m.setId(dto.getId());
		m.setDataHora(LocalDateTime.parse(dto.getDataHora(), formatter));
		m.setQuantidade(dto.getQuantidade());
		m.setEstoqueId(dto.getEstoqueId());
		m.setNovaFilialId(dto.getNovaFilialId());
		return m;
	}

}
