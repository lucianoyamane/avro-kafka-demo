package br.com.exemplo.producerservice.web;

import br.com.exemplo.contracts.avro.UserCreated;
import br.com.exemplo.producerservice.producer.UserCreatedProducer;
import java.time.OffsetDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserCreatedProducer producer;

  public UserController(UserCreatedProducer producer) {
    this.producer = producer;
  }

  @PostMapping
  public ResponseEntity<String> create(@RequestBody CreateUserRequest request) {
    UserCreated event = UserCreated.newBuilder()
        .setId(request.id())
        .setName(request.name())
        .setEmail(request.email())
        .setCreatedAt(OffsetDateTime.now().toString())
        .build();

    producer.send(event);

    return ResponseEntity.accepted().body("Evento publicado com sucesso");
  }
}
