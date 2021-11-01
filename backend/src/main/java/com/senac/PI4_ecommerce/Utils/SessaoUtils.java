package com.senac.PI4_ecommerce.Utils;

import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.senac.PI4_ecommerce.model.Cliente;
import com.senac.PI4_ecommerce.model.Usuario;

public class SessaoUtils {
	
	private SessaoUtils() {
	}
	
	public static Usuario getUsuarioDaSessao(HttpServletRequest req) {
		
		ServletContext session = req.getServletContext();
		Object userSessao = session.getAttribute("usuario");
		if(Objects.isNull(userSessao)) {
			throw new IllegalStateException("Usuario não setado na sessão, se logue novamente carinha");
		}
		
		return (Usuario) userSessao;
	}
	
	public static Cliente getClienteDaSessao(HttpServletRequest req) {
		
		ServletContext session = req.getServletContext();
		Object clienteSessao = session.getAttribute("cliente");
		if(Objects.isNull(clienteSessao)) {
			throw new IllegalStateException("Cliente não setado na sessão, se logue novamente carinha");
		}
		
		return (Cliente) clienteSessao;
	}

}
