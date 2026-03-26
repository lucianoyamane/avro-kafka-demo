# Schema Registry --- Versionamento e Compatibilidade

## Visão Geral

O Schema Registry é responsável por versionar e validar contratos de
dados em sistemas baseados em Kafka.

Ele garante que mudanças em schemas não quebrem consumidores existentes.

------------------------------------------------------------------------

## Conceito de Versionamento

### Subject

Um subject representa um conjunto de versões de um schema.

Exemplo: - pedido-criado-value

### Versionamento automático

Cada alteração gera uma nova versão: - v1 - v2 - v3

------------------------------------------------------------------------

## Exemplo

### v1

{ "type": "record", "name": "Pedido", "fields": \[ { "name": "id",
"type": "string" } \] }

### v2

{ "type": "record", "name": "Pedido", "fields": \[ { "name": "id",
"type": "string" }, { "name": "valor", "type": "double", "default": 0 }
\] }

------------------------------------------------------------------------

## Compatibilidade

Define regras para evolução de schemas.

### BACKWARD (padrão)

-   Novo schema lê dados antigos
-   Permite adicionar campos com default

### FORWARD

-   Schema antigo lê dados novos
-   Permite remover campos

### FULL

-   Compatível nos dois sentidos

### TRANSITIVE

-   Valida contra todas versões anteriores

------------------------------------------------------------------------

## Boas Práticas

### Mudanças seguras

-   Adicionar campo com default
-   Remover campo opcional

### Mudanças perigosas

-   Remover campo obrigatório
-   Alterar tipo de campo

------------------------------------------------------------------------

## Fluxo com Kafka

Producer: 1. Registra schema 2. Recebe ID 3. Envia mensagem

Consumer: 1. Lê ID 2. Consulta schema 3. Desserializa

------------------------------------------------------------------------

## Estratégias

### 1. Evolução no mesmo subject (recomendado)

pedido-criado-value v1 → v2 → v3

### 2. Versionamento no nome

pedido-criado-v1 / v2

### 3. Versionamento no payload (evitar)

------------------------------------------------------------------------

## Teste de Compatibilidade

POST /compatibility/subjects/{subject}/versions/latest

Resposta: { "is_compatible": true }

------------------------------------------------------------------------

## Governança

-   Schema Registry = fonte técnica
-   Documentação = fonte funcional

------------------------------------------------------------------------

## Regra de Ouro

Nunca faça mudanças que impeçam leitura de dados antigos.

------------------------------------------------------------------------

## Conclusão

Versionamento + compatibilidade garantem evolução segura e desacoplada
entre produtores e consumidores.
