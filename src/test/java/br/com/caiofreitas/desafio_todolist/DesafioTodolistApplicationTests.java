package br.com.caiofreitas.desafio_todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.caiofreitas.desafio_todolist.entity.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DesafioTodolistApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSucess() {
		var todo = new Todo("todo1", "desc todo 1", false, 1);

		webTestClient
		.post()
		.uri("/todos")
		.bodyValue(todo)
		.exchange()
		.expectStatus().isCreated()
		.expectBody()
		.jsonPath("$.name").isEqualTo(todo.getName())
		.jsonPath("$.descricao").isEqualTo(todo.getDescricao())
		.jsonPath("$.realizado").isEqualTo(todo.isRealizado())
		.jsonPath("$.prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	void testCreateTodoFailure() {
		webTestClient
		.post()
		.uri("/todos")
		.bodyValue(new Todo("", "", false, 0)).exchange()
		.expectStatus().isBadRequest();
	}

}
