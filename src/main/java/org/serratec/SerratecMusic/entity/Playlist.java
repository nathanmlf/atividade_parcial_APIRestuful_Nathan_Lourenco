package org.serratec.SerratecMusic.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "playlist")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Playlist.class)
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_playlist")
	@Schema(description = "ID único da playlist")
	private Long id;

	@NotBlank(message = "O nome da playlist não pode estar vazio!")
	@Size(max = 60)
	@Column(name = "nome_playlist", length = 60, nullable = false)
	@Schema(description = "Nome da playlist", example = "Clássicos do Rock")
	private String nome;

	@Size(max = 100)
	@Column(name = "descricao_playlist", length = 100, nullable = true)
	@Schema(description = "Descrição opcional da playlist", example = "As melhores do rock dos anos 80 e 90")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	@JsonBackReference
	private Usuario usuario;

	@ManyToMany
	@JoinTable(name = "playlist_musica", joinColumns = @JoinColumn(name = "id_playlist"), inverseJoinColumns = @JoinColumn(name = "id_musica"))
	@JsonManagedReference
	private Set<Musica> musicas = new HashSet<>();

	public Playlist() {
	}

	public Playlist(Long id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(Set<Musica> musicas) {
		this.musicas = musicas;
	}

}
