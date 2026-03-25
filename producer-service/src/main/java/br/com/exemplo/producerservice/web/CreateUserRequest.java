package br.com.exemplo.producerservice.web;

public record CreateUserRequest(
    String id,
    String name,
    String email,
    String phone
) {
}
