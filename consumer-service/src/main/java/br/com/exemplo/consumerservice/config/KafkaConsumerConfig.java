package br.com.exemplo.consumerservice.config;

import br.com.exemplo.contracts.avro.UserCreated;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConsumerConfig {

  @Value("${app.kafka.topic.user-created}")
  private String mainTopic;

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, UserCreated> kafkaListenerContainerFactory(
      ConsumerFactory<String, UserCreated> consumerFactory,
      DefaultErrorHandler errorHandler
  ) {
    ConcurrentKafkaListenerContainerFactory<String, UserCreated> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    factory.setCommonErrorHandler(errorHandler);
    return factory;
  }

  @Bean
  public KafkaTemplate<Object, Object> kafkaTemplate(ProducerFactory<Object, Object> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }

  @Bean
  public DefaultErrorHandler errorHandler(KafkaTemplate<Object, Object> kafkaTemplate) {
    DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(
        kafkaTemplate,
        (record, ex) -> new TopicPartition(record.topic() + "-dlt", record.partition())
    );

    FixedBackOff fixedBackOff = new FixedBackOff(2000L, 2L);

    return new DefaultErrorHandler(recoverer, fixedBackOff);
  }

  @Bean
  public NewTopic userCreatedTopic() {
    return new NewTopic(mainTopic, 1, (short) 1);
  }

  @Bean
  public NewTopic userCreatedDltTopic() {
    return new NewTopic(mainTopic + "-dlt", 1, (short) 1);
  }
}
