package br.com.exemplo.avro_kafka_demo.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.serialization.Serializer;

public class AvroSerializer<T extends SpecificRecord> implements Serializer<T> {

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    // sem configuração adicional
  }

  @Override
  public byte[] serialize(String topic, T data) {
    if (data == null) {
      return null;
    }

    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      SpecificDatumWriter<T> writer = new SpecificDatumWriter<>(data.getSchema());
      BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(outputStream, null);
      writer.write(data, encoder);
      encoder.flush();
      return outputStream.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException("Erro ao serializar objeto Avro", e);
    }
  }

  @Override
  public void close() {
    // nada a fechar
  }
}
