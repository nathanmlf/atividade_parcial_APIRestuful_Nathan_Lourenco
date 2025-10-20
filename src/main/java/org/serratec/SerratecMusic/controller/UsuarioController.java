package org.serratec.SerratecMusic.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.SerratecMusic.entity.Usuario;
import org.serratec.SerratecMusic.repository.UsuarioRepository;
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
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	@Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de todos os usuários cadastradas")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso") })
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	@GetMapping("{id}")
	@Operation(summary = "Busca um usuário por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado") })
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Operation(summary = "Cria um novo usuário")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos") })
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario criar(@Valid @RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@PostMapping("/lista")
	@Operation(summary = "Cria uma lista de novos usuários", description = "Adiciona múltiplos usuários ao banco de dados em uma única requisição. O corpo da requisição deve ser uma lista (array) de objetos Usuário.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Lista de usuários criada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos em um ou mais usuários da lista") })
	@ResponseStatus(HttpStatus.CREATED)
	public List<Usuario> criarLista(@Valid @RequestBody List<Usuario> usuario) {
		return usuarioRepository.saveAll(usuario);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um usuário existente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrada") })
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		if (usuarioRepository.existsById(id)) {
			usuario.setId(id);
			return ResponseEntity.ok(usuarioRepository.save(usuario));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id}")
	@Operation(summary = "Deleta um usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso, o perfil também foi deletado."),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado") })
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}