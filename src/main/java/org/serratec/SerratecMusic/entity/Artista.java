package org.serratec.SerratecMusic.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "artista")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Playlist.class)
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "ID único do artista")
	@Column(name = "id_artista")
	private Long id;

	@NotBlank(message = "O nome do artista não pode estar vazio!")
	@Size(max = 60)
	@Schema(description = "Nome do artista ou da banda", example = "Queen")
	@Column(name = "nome_artista", length = 60, nullable = false)
	private String nome;

	@NotBlank(message = "A nacionalidade do artista não pode estar vazia!")
	@Size(max = 20)
	@Column(name = "nacionalidade_artista", length = 20, nullable = false)
	@Schema(description = "Nacionalidade do artista", example = "Britânica")
	private String nacionalidade;

	@ManyToMany(mappedBy = "artistas")
	@JsonBackReference
	private Set<Musica> musicas = new HashSet<>();

	public Artista() {
	}

	public Artista(Long id, String nome, String nacionalidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public Set<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(Set<Musica> musicas) {
		this.musicas = musicas;
	}

}
