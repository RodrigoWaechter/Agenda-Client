package com.projeto.agenda.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.projeto.agenda.components.InterfaceCRUD;
import com.projeto.agenda.server.service.ClienteService;

@Controller
public class ClienteController implements InterfaceCRUD {

	@Autowired
	private ClienteService clienteService;

	@Override
	public void salvar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscarTodos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	
}
