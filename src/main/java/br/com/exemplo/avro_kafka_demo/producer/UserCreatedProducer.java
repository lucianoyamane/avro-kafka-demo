package br.com.exemplo.avro_kafka_demo.producer;

import br.com.exemplo.avro.schema.UserCreated;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedProducer {

  private final KafkaTemplate<String, UserCreated> kafkaTemplate;

  @Value("${app.kafka.topic.user-created}")
  private String topic;

  public UserCreatedProducer(KafkaTemplate<String, UserCreated> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void send(UserCreated event) {
    kafkaTemplate.send(topic, event.getId().toString(), event);
  }

}
