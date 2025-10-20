package org.serratec.SerratecMusic.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.SerratecMusic.entity.Playlist;
import org.serratec.SerratecMusic.repository.PlaylistRepository;
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
@RequestMapping("/playlists")
public class PlaylistController {

	@Autowired
	private PlaylistRepository playlistRepository;

	@GetMapping
	@Operation(summary = "Lista todas as playlists", description = "Retorna uma lista de todas as playlists cadastradas")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso") })
	public ResponseEntity<List<Playlist>> listar() {
		return ResponseEntity.ok(playlistRepository.findAll());
	}

	@GetMapping("{id}")
	@Operation(summary = "Busca uma playlist por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Playlist encontrada"),
			@ApiResponse(responseCode = "404", description = "Playlist não encontrada") })
	public ResponseEntity<Playlist> buscarPorId(@PathVariable Long id) {
		Optional<Playlist> playlist = playlistRepository.findById(id);

		if (playlist.isPresent()) {
			return ResponseEntity.ok(playlist.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Operation(summary = "Cria uma nova playlist")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Playlist criada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos") })
	@ResponseStatus(HttpStatus.CREATED)
	public Playlist criar(@Valid @RequestBody Playlist playlist) {
		return playlistRepository.save(playlist);
	}

	@PostMapping("/lista")
	@Operation(summary = "Cria uma lista de novas playlists", description = "Adiciona múltiplas playlists ao banco de dados em uma única requisição. O corpo da requisição deve ser uma lista (array) de objetos Playlist.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Lista de playlists criada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos em um ou mais playlists da lista") })
	@ResponseStatus(HttpStatus.CREATED)
	public List<Playlist> criarLista(@Valid @RequestBody List<Playlist> playlist) {
		return playlistRepository.saveAll(playlist);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza uma playlist existente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Playlist atualizada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos"),
			@ApiResponse(responseCode = "404", description = "Playlist não encontrada") })
	public ResponseEntity<Playlist> atualizar(@PathVariable Long id, @Valid @RequestBody Playlist playlist) {
		if (!playlistRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		playlist.setId(id);

		Playlist playlistSalva = playlistRepository.save(playlist);

		return ResponseEntity.ok(playlistSalva);
	}

	@DeleteMapping("{id}")
	@Operation(summary = "Deleta uma playlist")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Playlist deletada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Playlist não encontrada") })
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (playlistRepository.existsById(id)) {
			playlistRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
