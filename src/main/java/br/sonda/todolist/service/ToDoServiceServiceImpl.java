package br.sonda.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sonda.todolist.entity.ToDo;
import br.sonda.todolist.exception.ToDoException;
import br.sonda.todolist.repository.TodoRepository;

@Service
public class ToDoServiceServiceImpl implements ToDoService {
	
	@Autowired
	TodoRepository repository;
		
	@Override
	public Optional<ToDo> buscar(Long id) throws ToDoException {
		return repository.findById(id);
	}

	@Override
	public ToDo gravar(ToDo toDo) throws ToDoException {
		return repository.save(toDo);
	}

	@Override
	public void remover(ToDo toDo) throws ToDoException {
		repository.delete(toDo);
		
	}

	@Override
	public List<ToDo> listarTodos() throws ToDoException {
		return repository.findAll();
	}

	


}
