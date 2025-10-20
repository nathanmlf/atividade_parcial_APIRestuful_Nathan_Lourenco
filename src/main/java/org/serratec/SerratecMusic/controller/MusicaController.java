package org.serratec.SerratecMusic.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.SerratecMusic.entity.Musica;
import org.serratec.SerratecMusic.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

	@Autowired
	private MusicaRepository musicaRepository;

	@GetMapping
	@Operation(summary = "Lista todas as músicas", description = "Retorna uma lista de todas as músicas cadastradas")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso") })
	public ResponseEntity<List<Musica>> listar() {
		return ResponseEntity.ok(musicaRepository.findAll());
	}

	@GetMapping("{id}")
	@Operation(summary = "Busca uma música por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Música encontrada"),
			@ApiResponse(responseCode = "404", description = "Música não encontrada") })
	public ResponseEntity<Musica> buscarPorId(@PathVariable Long id) {
		Optional<Musica> musica = musicaRepository.findById(id);

		if (musica.isPresent()) {
			return ResponseEntity.ok(musica.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Operation(summary = "Cria uma nova música")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Música criada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos") })
	@ResponseStatus(HttpStatus.CREATED)
	public Musica criar(@Valid @RequestBody Musica musica) {
		return musicaRepository.save(musica);
	}

	@PostMapping("/lista")
	@Operation(summary = "Cria uma lista de novas músicas", description = "Adiciona múltiplas músicas ao banco de dados em uma única requisição. O corpo da requisição deve ser uma lista (array) de objetos Música.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Lista de músicas criada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos em um ou mais músicas da lista") })
	@ResponseStatus(HttpStatus.CREATED)
	public List<Musica> criarLista(@Valid @RequestBody List<Musica> musica) {
		return musicaRepository.saveAll(musica);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza uma música existente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Música atualizada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos"),
			@ApiResponse(responseCode = "404", description = "Música não encontrada") })
	public ResponseEntity<Musica> atualizar(@PathVariable Long id, @Valid @RequestBody Musica musica) {
		if (musicaRepository.existsById(id)) {
			musica.setId(id);
			return ResponseEntity.ok(musicaRepository.save(musica));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id}")
	@Operation(summary = "Deleta uma música")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Música deletada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Música não encontrada") })
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (musicaRepository.existsById(id)) {
			musicaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}