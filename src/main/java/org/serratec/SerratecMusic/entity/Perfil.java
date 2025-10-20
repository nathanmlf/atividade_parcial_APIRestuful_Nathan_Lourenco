package org.serratec.SerratecMusic.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "perfil")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Playlist.class)
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil")
	@Schema(description = "ID único do perfil")
	private Long id;

	@NotBlank(message = "O telefone não pode estar vazio!")
	@Size(max = 20)
	@Column(name = "telefone_perfil", length = 20, nullable = false)
	@Schema(description = "Número de telefone do usuário", example = "(24)99887-6655") 
	private String telefone;

	@NotNull(message = "Data de nascimento não pode estar vazia!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Past(message = "A data de nascimento deve ser no passado!")
	@Column(name = "data_nascimento_perfil", nullable = false)
	@Schema(description = "Data de nascimento no formato dd/MM/yyyy", example = "25/08/1990")
	private LocalDate dataNascimento;

	@OneToOne(mappedBy = "perfil")
	@JsonBackReference
	private Usuario usuario;

	public Perfil() {
	}

	public Perfil(Long id, String telefone, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
