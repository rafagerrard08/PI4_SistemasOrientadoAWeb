package com.senac.PI4_ecommerce.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import com.senac.PI4_ecommerce.dto.UsuarioDTO;
import com.senac.PI4_ecommerce.model.enums.EstadoUsuario;
import com.senac.PI4_ecommerce.model.enums.TipoUsuario;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@Email
	private String email;
	@CPF
	private String cpf;
	private String senha;
	private Integer tipoUsuario; // Administrador ou Estoquista
	private Integer estadoUsuario;

	public Usuario(UsuarioDTO usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.cpf = usuario.getCpf();
		this.tipoUsuario = usuario.getTipoUsuario().getId();
		this.estadoUsuario = usuario.getEstadoUsuario().getId();
	}

	public Usuario() {
	}

	public Usuario(Integer id, String nome, String email, String cpf, String senha, Integer tipoUsuario, Integer estadoUsuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.estadoUsuario = estadoUsuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getTipoUsuario() {
		return TipoUsuario.toEnum(tipoUsuario);
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario.getId();
	}
	
	public EstadoUsuario getEstadoUsuario() {
		return EstadoUsuario.toEnum(estadoUsuario);
	}

	public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
		this.estadoUsuario = estadoUsuario.getId();
	}

}
