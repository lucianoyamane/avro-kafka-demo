# UserCreated

## Objetivo
Evento publicado quando um usuário é criado no sistema.

## Subject no Schema Registry
`user-created-topic-value`

## Política de compatibilidade
`BACKWARD`

## Histórico de versões

### v1
#### Schema
- id
- name
- email
- createdAt

#### Motivação
Versão inicial do evento.

#### Compatibilidade
Base inicial.

#### Impacto esperado
Nenhum, evento inicial.

---

### v2
#### Alterações
- adicionado campo `phone`

#### Tipo de mudança
Compatível

#### Justificativa
Necessidade de trafegar telefone no evento sem quebrar consumidores existentes.

#### Compatibilidade esperada
- consumer novo lê mensagens antigas: sim
- consumer antigo lê mensagens novas: sim, desde que ignore o campo novo

#### Observações
Campo definido como opcional com default `null`.

---

### v3
#### Alterações
- removido campo `email`

#### Tipo de mudança
Aceita pelo Registry sob política `BACKWARD`

#### Justificativa
Campo deixou de ser necessário no novo modelo do evento.

#### Atenção
Apesar de aceita pelo Registry, essa alteração pode impactar consumidores antigos que ainda esperam `email`.

#### Compatibilidade esperada
- consumer novo lê mensagens antigas: sim
- consumer antigo lê mensagens novas: pode falhar

#### Decisão arquitetural
Remoção de campo exige análise explícita de consumidores existentes antes de promover a mudança.