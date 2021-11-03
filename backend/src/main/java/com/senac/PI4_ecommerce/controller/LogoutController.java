package com.senac.PI4_ecommerce.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/logout")
public class LogoutController {

	@GetMapping
	public ResponseEntity<?> getProdutosPage(HttpServletRequest req) {

		ServletContext session = req.getServletContext();
		session.setAttribute("usuario", null);
		session.setAttribute("cliente", null);
				
		return ResponseEntity.ok().build();
	}

}
