package com.projeto.agenda.client.controller;

import com.projeto.agenda.client.form.LoginForm;
import com.projeto.agenda.components.DefaultController;
import com.projeto.agenda.server.domain.Usuario;

public class LoginController extends DefaultController<Usuario>{
	private final LoginForm form;
	
	public LoginController(LoginForm form) {
		this.form = form;
	}
	
	public void login() {
		
	}
}
