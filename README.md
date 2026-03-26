# 📦 Avro + Kafka + Schema Registry (Spring Boot)

## 🧭 Visão Geral

Este projeto demonstra a integração entre:

- Spring Boot
- Apache Kafka
- Apache Avro
- Schema Registry (Confluent)

Fluxo completo de produção e consumo de eventos com contrato versionado.

---

## 🎯 Objetivo

- Definir contrato via Avro
- Gerar classes Java
- Produzir e consumir eventos Kafka
- Registrar schemas automaticamente
- Evolução segura de contrato

---

## 🧱 Arquitetura

[ REST API ] → [ Producer ] → [ Kafka ] → [ Consumer ]
                     ↓
               Schema Registry

---

## 🧬 Contrato (Avro)

Arquivo:

src/main/avro/UserCreated.avsc

```json
{
  "type": "record",
  "namespace": "br.com.exemplo.avro.schema",
  "name": "UserCreated",
  "fields": [
    { "name": "id", "type": "string" },
    { "name": "name", "type": "string" },
    { "name": "email", "type": "string" },
    { "name": "createdAt", "type": "string" }
  ]
}
```

---

## ⚙️ Geração de Classes

```bash
mvn clean compile
```

---

## 🐳 Infraestrutura

```bash
docker compose up -d
```

---

## 🔌 Configuração

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:29092
    properties:
      schema.registry.url: http://localhost:8081
```

---

## 🚀 Execução

```bash
mvn spring-boot:run
```

---

## 📡 Teste

```bash
curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{"id":"1","name":"Luciano","email":"luciano@email.com"}'
```

---

## 📚 Schema Registry

```bash
curl http://localhost:8081/subjects
```
validar Schema Registry
listar versões
```bash
curl http://localhost:8081/subjects/user-created-topic-value/versions
```
ver última versão
```bash
curl http://localhost:8081/subjects/user-created-topic-value/versions/latest
```
---

## 🧠 Conceitos

- Avro = contrato + binário
- Schema Registry = versionamento
- SpecificRecord = tipagem forte

---

## 🔄 Evolução

Adicionar campo com default:

```json
{
  "name": "phone",
  "type": ["null", "string"],
  "default": null
}
```

---

## ⚠️ Problemas comuns

- Kafka não conecta → porta errada
- Registry não responde → container não subiu
- ClassCast → faltou specific.avro.reader

---

## 💡 Insight

Contrato como fonte da verdade → desacoplamento real
