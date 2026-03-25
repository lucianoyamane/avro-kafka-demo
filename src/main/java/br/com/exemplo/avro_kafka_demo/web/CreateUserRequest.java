package br.com.exemplo.avro_kafka_demo.web;

public record CreateUserRequest( String id,
                                 String name,
                                 String email) {

}
