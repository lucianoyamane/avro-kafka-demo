package br.com.exemplo.producerservice.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import br.com.exemplo.contracts.avro.UserCreated;

@Component
public class UserCreatedProducer {

  private final KafkaTemplate<String, UserCreated> kafkaTemplate;

  @Value("${app.kafka.topic.user-created}")
  private String topic;

  public UserCreatedProducer(KafkaTemplate<String, UserCreated> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void send(UserCreated event) {
    String key = event.getId() != null ? event.getId().toString() : null;
    kafkaTemplate.send(topic, key, event);
  }
}
