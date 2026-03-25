package br.com.exemplo.avro_kafka_demo.consumer;

import br.com.exemplo.avro.schema.UserCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedConsumer {

  @KafkaListener(
      topics = "${app.kafka.topic.user-created}",
      groupId = "${spring.kafka.consumer.group-id}"
  )
  public void consume(UserCreated event) {
    System.out.println("Mensagem recebida:");
    System.out.println("id = " + event.getId());
    System.out.println("name = " + event.getName());
    System.out.println("email = " + event.getEmail());
    System.out.println("createdAt = " + event.getCreatedAt());
  }

}
