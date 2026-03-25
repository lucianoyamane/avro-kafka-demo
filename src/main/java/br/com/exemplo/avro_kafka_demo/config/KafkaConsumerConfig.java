package br.com.exemplo.avro_kafka_demo.config;

import br.com.exemplo.avro.schema.UserCreated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaConsumerConfig {

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, UserCreated> kafkaListenerContainerFactory(
      ConsumerFactory<String, UserCreated> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, UserCreated> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    return factory;
  }
}
