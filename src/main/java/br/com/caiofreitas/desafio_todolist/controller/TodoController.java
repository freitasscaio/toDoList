package br.com.caiofreitas.desafio_todolist.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caiofreitas.desafio_todolist.entity.Todo;
import br.com.caiofreitas.desafio_todolist.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody Todo todo) { // Retorna ResponseEntity de um único Todo
        Todo createdTodo = todoService.create(todo);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> list(){
        return new ResponseEntity<>(todoService.list(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<List<Todo>> update(@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.update(todo), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<Todo>> delete(@PathVariable("id") Long id){
        return new ResponseEntity<>(todoService.delete(id), HttpStatus.OK);
    }
}
