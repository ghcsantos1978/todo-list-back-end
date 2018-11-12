package br.sonda.todolist.service;

import java.util.List;
import java.util.Optional;

import br.sonda.todolist.entity.ToDo;
import br.sonda.todolist.exception.ToDoException;

public interface ToDoService {


	public Optional<ToDo> buscar(Long id) throws ToDoException;
	
	public ToDo gravar(ToDo toDo) throws ToDoException;
	
	public void remover(ToDo toDo) throws ToDoException;
	
	public List<ToDo> listarTodos() throws ToDoException;

	
	
}
