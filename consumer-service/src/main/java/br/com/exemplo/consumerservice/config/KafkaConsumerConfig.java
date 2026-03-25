package br.com.exemplo.consumerservice.config;

import br.com.exemplo.contracts.avro.UserCreated;
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
