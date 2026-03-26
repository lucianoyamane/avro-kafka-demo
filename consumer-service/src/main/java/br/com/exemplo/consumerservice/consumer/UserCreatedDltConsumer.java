package br.com.exemplo.consumerservice.consumer;

import br.com.exemplo.contracts.avro.UserCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedDltConsumer {

  @KafkaListener(
      topics = "${app.kafka.topic.user-created}-dlt",
      groupId = "user-created-dlt-group"
  )
  public void consumeDlt(UserCreated event) {
    System.out.println("Mensagem recebida na DLT:");
    System.out.println("id = " + event.getId());
    System.out.println("name = " + event.getName());
    System.out.println("email = " + event.getEmail());
    System.out.println("createdAt = " + event.getCreatedAt());
    System.out.println("phone = " + event.getPhone());
  }
}
