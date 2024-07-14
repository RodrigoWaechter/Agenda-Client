package com.projeto.agenda.components;

public interface InterfaceCRUD {

	abstract void salvar(Integer id);
	abstract void atualizar(Integer id);
	abstract void excluir(Integer id);
	abstract void buscarTodos();
	abstract void buscarPorId(Integer id);
	
}
