package org.serratec.SerratecMusic.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.SerratecMusic.entity.Artista;
import org.serratec.SerratecMusic.repository.ArtistaRepository;
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
@RequestMapping("/artistas")
public class ArtistaController {

	@Autowired
	private ArtistaRepository artistaRepository;

	@GetMapping
	@Operation(summary = "Lista todos os artistas", description = "Retorna uma lista de todos os artistas cadastrados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso") })
	public ResponseEntity<List<Artista>> listar() {
		return ResponseEntity.ok(artistaRepository.findAll());
	}

	@GetMapping("{id}")
	@Operation(summary = "Busca um artista por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Artista encontrado"),
			@ApiResponse(responseCode = "404", description = "Artista não encontrado") })
	public ResponseEntity<Artista> buscarPorId(@PathVariable Long id) {
		Optional<Artista> artista = artistaRepository.findById(id);

		if (artista.isPresent()) {
			return ResponseEntity.ok(artista.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Operation(summary = "Cria um novo artista")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Artista criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos") })
	@ResponseStatus(HttpStatus.CREATED)
	public Artista criar(@Valid @RequestBody Artista artista) {
		return artistaRepository.save(artista);
	}

	@PostMapping("/lista")
	@Operation(summary = "Cria uma lista de novos artistas", description = "Adiciona múltiplos artistas ao banco de dados em uma única requisição. O corpo da requisição deve ser uma lista (array) de objetos Artista.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Lista de artistas criada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos em um ou mais artistas da lista") })
	@ResponseStatus(HttpStatus.CREATED)
	public List<Artista> criarLista(@Valid @RequestBody List<Artista> artista) {
		return artistaRepository.saveAll(artista);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um artista existente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Artista atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos"),
			@ApiResponse(responseCode = "404", description = "Artista não encontrado") })
	public ResponseEntity<Artista> atualizar(@PathVariable Long id, @Valid @RequestBody Artista artista) {
		if (artistaRepository.existsById(id)) {
			artista.setId(id);
			return ResponseEntity.ok(artistaRepository.save(artista));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id}")
	@Operation(summary = "Deleta um artista")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Artista deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Artista não encontrado") })
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (artistaRepository.existsById(id)) {
			artistaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
