# Confluent Schema Registry (cp-schema-registry)

## Visão Geral

O Schema Registry é um serviço REST da Confluent utilizado para
gerenciar schemas (Avro, JSON Schema e Protobuf) usados em mensagens
Kafka.

Ele permite: - Versionamento de contratos - Validação de
compatibilidade - Centralização de schemas - Integração com producers e
consumers

------------------------------------------------------------------------

## Arquitetura

-   Serviço HTTP (porta padrão: 8081)
-   Armazena dados no Kafka
-   Integrado com serializers da Confluent

------------------------------------------------------------------------

## Configurações Principais

-   SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS
-   SCHEMA_REGISTRY_HOST_NAME
-   SCHEMA_REGISTRY_LISTENERS

------------------------------------------------------------------------

## Principais Endpoints

### Listar subjects

GET /subjects

### Listar versões

GET /subjects/{subject}/versions

### Buscar schema

GET /subjects/{subject}/versions/{version}

### Registrar schema

POST /subjects/{subject}/versions

### Buscar por ID

GET /schemas/ids/{id}

### Compatibilidade

POST /compatibility/subjects/{subject}/versions/{version}

### Configuração

GET /config PUT /config

------------------------------------------------------------------------

## Compatibilidade

-   backward (padrão)
-   forward
-   full
-   none
-   versões transitive

------------------------------------------------------------------------

## Exemplo de Execução

docker run -d\
-p 8081:8081\
-e SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS=PLAINTEXT://kafka:9092\
confluentinc/cp-schema-registry:latest

------------------------------------------------------------------------

## Fluxo

1.  Producer envia evento
2.  Serializer registra schema
3.  Kafka armazena mensagem com ID do schema
4.  Consumer consulta schema e desserializa

------------------------------------------------------------------------

## Conclusão

O Schema Registry é essencial para garantir evolução segura de contratos
em sistemas baseados em Kafka.
