package com.senac.PI4_ecommerce.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import com.senac.PI4_ecommerce.model.Usuario;
import com.senac.PI4_ecommerce.model.enums.TipoUsuario;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	@Email
	private String email;
	@CPF
	private String cpf;
	private Integer tipoUsuario; // Administrador ou Estoquista

	public UsuarioDTO(Integer id, String nome, String email, String cpf, String senha, Integer tipoUsuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.tipoUsuario = tipoUsuario;
	}
	
	public UsuarioDTO() {

	}

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.cpf = usuario.getCpf();
		this.tipoUsuario = usuario.getTipoUsuario().getId();
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public TipoUsuario getTipoUsuario() {
		return TipoUsuario.toEnum(tipoUsuario);
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario.getId();
	}

}
