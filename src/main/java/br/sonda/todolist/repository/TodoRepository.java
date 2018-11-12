package br.sonda.todolist.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.sonda.todolist.entity.ToDo;


@Repository
public interface TodoRepository extends MongoRepository<ToDo, Long> {


	
}
