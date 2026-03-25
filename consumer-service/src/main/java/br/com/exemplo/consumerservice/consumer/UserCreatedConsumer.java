package br.com.exemplo.consumerservice.consumer;

import br.com.exemplo.contracts.avro.UserCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedConsumer {

  @KafkaListener(
      topics = "${app.kafka.topic.user-created}",
      groupId = "${spring.kafka.consumer.group-id}"
  )
  public void consume(UserCreated event) {
    String email = event.getEmail() != null ? event.getEmail().toString() : null;

    System.out.println("Consumindo evento...");
    System.out.println("id = " + event.getId());
    System.out.println("name = " + event.getName());
    System.out.println("email = " + event.getEmail());
    System.out.println("createdAt = " + event.getCreatedAt());
    System.out.println("phone = " + event.getPhone());

    if (email != null && email.contains("erro")) {
      throw new IllegalArgumentException("Erro simulado de negócio para envio à DLT");
    }

    System.out.println("Evento processado com sucesso.");
  }
}
