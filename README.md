# Projeto Cars

## Configuração

* **Ferramenta de Teste:** Thunder Client / Postman
* **URL Base:** `http://localhost:8080`

## Guia de Teste Passo a Passo

Siga esta ordem para validar o fluxo principal e as regras de negócio.

### PASSO 1: Preparação - Cadastro de Entidades

Crie um passageiro e um motorista. **Anote os IDs** que serão usados para as viagens.

#### 1.1 Criar Passageiro

* **Endpoint:** `POST /passengers`
* **Corpo:**
    ```json
    {"name": "Ana Cliente", "phone": "4599991111"}
    ```
    *(Anote o ID retornado, ex: `ID_PASSAGEIRO = 1`)*

#### 1.2 Criar Motorista

* **Endpoint:** `POST /drivers`
* **Corpo:**
    ```json
    {"name": "Carlos Driver", "birthDate": "1980-01-01", "email": "carlos@car.com", "cpf": "111.111.111-11", "placa": "AAA1B23", "cnh": "12345678901", "anoCarro": 2020, "comentario": "Carro novo."}
    ```
    *(Anote o ID retornado, ex: `ID_MOTORISTA = 1`)*

---

### PASSO 2: Fluxo Principal - Aceitar e Finalizar (Sucesso)

Este fluxo demonstra a transição `CREATED` $\to$ `ACCEPTED` $\to$ `FINISHED`.

#### 2.1 Criar Viagem

* **Endpoint:** `POST /travels`
* **Ação:** Passageiro solicita.
* **Corpo:**
    ```json
    {"origem": "Casa", "destino": "Trabalho", "passengerId": ID_PASSAGEIRO}
    ```
    *(Anote o ID da viagem, ex: `ID_VIAGEM_A`)*

#### 2.2 Aceitar Viagem (Transição para ACCEPTED)

* **Endpoint:** `POST /travels/ID_VIAGEM_A/accept?driverId=ID_MOTORISTA`
* **Ação:** Motorista aceita.
* **Resultado:** Status deve ser `ACCEPTED`.

#### 2.3 Finalizar Viagem (Transição para FINISHED)

* **Endpoint:** `POST /travels/ID_VIAGEM_A/finish`
* **Ação:** Motorista finaliza a corrida.
* **Resultado:** Status deve ser `FINISHED`.

---

### PASSO 3: Teste do Fluxo de Recusa

Este teste valida a transição para `REFUSED` e a rejeição de aceitação posterior.

#### 3.1 Criar Viagem de Teste (Recusa)

* **Endpoint:** `POST /travels`
* **Corpo:** `{"origem": "Ponto X", "destino": "Ponto Y", "passengerId": ID_PASSAGEIRO}`
    *(Anote o ID da viagem, ex: `ID_VIAGEM_R`)*

#### 3.2 Recusar Viagem

* **Endpoint:** `POST /travels/ID_VIAGEM_R/refuse?driverId=ID_MOTORISTA`
* **Ação:** Motorista recusa a viagem.
* **Resultado:** Status deve ser `REFUSED`.

#### 3.3 TESTE DE ERRO: Tentar Aceitar Viagem Recusada

* **Endpoint:** `POST /travels/ID_VIAGEM_R/accept?driverId=ID_MOTORISTA`
* **Resultado Esperado:** `400 Bad Request` (Viagem só pode ser aceita se o status for `CREATED`).

---
