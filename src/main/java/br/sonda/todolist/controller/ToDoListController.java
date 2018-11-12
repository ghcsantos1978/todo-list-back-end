package br.sonda.todolist.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.sonda.todolist.entity.ToDo;
import br.sonda.todolist.exception.ToDoException;
import br.sonda.todolist.service.ToDoService;

@RestController
@RequestMapping("/todoList")
@CrossOrigin("*")
public class ToDoListController {

	@Autowired
	private ToDoService service;
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<ToDo> buscarTarefa(HttpServletResponse response,@PathVariable @NotNull Long id){
		try {
			Optional<ToDo> todoDo = service.buscar(id);
			if (todoDo==null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
			return todoDo;
		} catch (ToDoException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}
	
	@RequestMapping(value = "/inserir",headers = {"content-type=application/json"},consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> gravar(HttpServletResponse response,@Valid @RequestBody ToDo toDo) {
		try {
			Map<String,String> retorno = new HashMap<String,String>();
			ToDo todo = service.gravar(toDo);
			if (todo!=null && todo.getId()!=null) {
				retorno = Collections.singletonMap("retorno", "TRUE"); 
			}
			else {
				retorno = Collections.singletonMap("retorno", "FALSE"); 
			}
			return retorno;
		} catch (ToDoException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}	
		
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	@ResponseBody
	public List<ToDo> listarTodos(HttpServletResponse response){
		try {
			return service.listarTodos();
		}
		catch(ToDoException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	
	}
	

	@RequestMapping(value = "/excluir",headers = {"content-type=application/json"},consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public  Map<String,String> excluir(@Valid @RequestBody ToDo toDo){
		try {
			service.remover(toDo);
			return Collections.singletonMap("retorno", "TRUE"); 
		} catch (ToDoException e) {
			return Collections.singletonMap("retorno", "FALSE"); 
		}
	}
	
	
}
