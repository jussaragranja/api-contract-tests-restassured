# 🧪 Contract Tests - Java 25 - JUnit5 - RestAssured - NetworkNT

Projeto exemplo de testes de contrato para APIs REST utilizando boas
práticas modernas de QA.

------------------------------------------------------------------------

## 🚀 Stack

-   Java 25
-   JUnit 5
-   RestAssured
-   JSON Schema Validator
-   NetworkNT Validator

------------------------------------------------------------------------

## 📁 Estrutura

    contract/
     ├── config/        → Configuração (multi-env)
     ├── client/        → Camada de acesso à API
     ├── validator/     → Validação de contrato (DRY)
     ├── test/          → Testes

------------------------------------------------------------------------

## ⚙️ Configuração

Arquivos por ambiente:

    application-dev.yml
    application-qa.yml
    application-prod.yml

Executar com:

``` bash
mvn test -Denv=dev
```

------------------------------------------------------------------------

## 🧪 Tipos de validação

### 1) NetworkNT

✔ Controle de versão (Draft 2020-12)\
✔ Mensagens detalhadas\
✔ Melhor debug

------------------------------------------------------------------------

### 2) RestAssured Schema Validator

✔ Simples\
✔ Mais Comum

------------------------------------------------------------------------

## 🧠 Boas práticas aplicadas

### ✔ Separação de responsabilidades

-   Client → chamadas HTTP
-   Validator → validação
-   Test → orquestração

------------------------------------------------------------------------

### ✔ DRY (Don't Repeat Yourself)

Validação centralizada em:

    ContractValidator

------------------------------------------------------------------------

### ✔ Multi-environment

Suporte via:

``` bash
-Denv=dev
-Denv=qa
-Denv=prod
```

------------------------------------------------------------------------

### ✔ Validações além do schema

Os testes validam:

-   Status code
-   Content-Type
-   Tempo de resposta
-   Body não vazio

------------------------------------------------------------------------

## ⚠️ Utilização de `additionalProperties: false`

Usado nos schemas para garantir contrato rígido.

### Impacto:

✔ Evita mudanças silenciosas\
❌ Quebra testes ao adicionar novos campos

------------------------------------------------------------------------

## ❌ Testes negativos

Exemplo incluído:

-   JSON inválido
-   Campos extras
-   Formato incorreto

------------------------------------------------------------------------

## 📌 Objetivo do projeto

Este projeto serve como:

-   Template open source
-   Guia para iniciantes
-   Base real para times de QA

------------------------------------------------------------------------

## ▶️ Executando os testes

``` bash
mvn test
```

------------------------------------------------------------------------

## 💡 Dica final

Contrato não é só schema.

Testa também:

-   Headers
-   Status
-   Performance
-   Regras de negócio críticas
