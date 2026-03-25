package br.com.exemplo.avro_kafka_demo.config;

import br.com.exemplo.avro.schema.UserCreated;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  @Bean
  public KafkaTemplate<String, UserCreated> kafkaTemplate(
      org.springframework.kafka.core.ProducerFactory<String, UserCreated> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }

}
