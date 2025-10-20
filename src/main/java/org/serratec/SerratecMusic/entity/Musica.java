package org.serratec.SerratecMusic.entity;

import java.util.HashSet;
import java.util.Set;

import org.serratec.SerratecMusic.enums.GeneroMusical;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "musica")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Playlist.class)
public class Musica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_musica")
	@Schema(description = "ID único da música")
	private Long id;

	@NotBlank(message = "O título da música não pode estar vazio!")
	@Size(max = 100)
	@Column(name = "titulo_musica", length = 100, nullable = false)
	@Schema(description = "Título da música", example = "Bohemian Rhapsody")
	private String titulo;

	@NotNull(message = "A minutagem da música não pode estar vazia!")
	@Positive(message = "A minutagem da música não pode ser negativa!")
	@Schema(description = "Duração da música em minutos", example = "6")
	@Column(name = "minutos_musica", nullable = false)
	private Integer minutos;

	@NotNull(message = "Gênero musical não pode estar vazio!")
	@Enumerated(EnumType.STRING)
	@Column(name = "genero_musical", nullable = false)
	@Schema(description = "Gênero musical da faixa. Valores válidos: ROCK, POP, SAMBA, FUNK, SERTANEJO.", example = "ROCK") 
	protected GeneroMusical generoMusical;

	@ManyToMany(mappedBy = "musicas")
	@JsonBackReference
	private Set<Playlist> playlists = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "musica_artista", joinColumns = @JoinColumn(name = "id_musica"), inverseJoinColumns = @JoinColumn(name = "id_artista"))
	@JsonManagedReference
	private Set<Artista> artistas = new HashSet<>();

	public Musica() {
	}

	public Musica(Long id, String titulo, Integer minutos, GeneroMusical generoMusical) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.minutos = minutos;
		this.generoMusical = generoMusical;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getMinutos() {
		return minutos;
	}

	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}

	public GeneroMusical getGeneroMusical() {
		return generoMusical;
	}

	public void setGeneroMusical(GeneroMusical generoMusical) {
		this.generoMusical = generoMusical;
	}

	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Set<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(Set<Artista> artistas) {
		this.artistas = artistas;
	}

}
