package org.serratec.SerratecMusic.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.SerratecMusic.entity.Perfil;
import org.serratec.SerratecMusic.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

	@Autowired
	private PerfilRepository perfilRepository;

	@GetMapping
	@Operation(summary = "Lista todos os perfils", description = "Retorna uma lista de todos os perfis cadastrados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso") })
	public ResponseEntity<List<Perfil>> listar() {
		return ResponseEntity.ok(perfilRepository.findAll());
	}

	@GetMapping("{id}")
	@Operation(summary = "Busca um perfil por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Perfil encontrado"),
			@ApiResponse(responseCode = "404", description = "Perfil não encontrado") })
	public ResponseEntity<Perfil> buscarPorId(@PathVariable Long id) {
		Optional<Perfil> perfil = perfilRepository.findById(id);

		if (perfil.isPresent()) {
			return ResponseEntity.ok(perfil.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um perfil existente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Perfil atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos"),
			@ApiResponse(responseCode = "404", description = "Perfil não encontrado") })
	public ResponseEntity<Perfil> atualizar(@PathVariable Long id, @Valid @RequestBody Perfil perfil) {
		if (perfilRepository.existsById(id)) {
			perfil.setId(id);
			return ResponseEntity.ok(perfilRepository.save(perfil));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id}")
	@Operation(summary = "Deleta um perfil")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Perfil deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Perfil não encontrado") })
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (perfilRepository.existsById(id)) {
			perfilRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}