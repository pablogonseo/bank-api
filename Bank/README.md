# Bank API - Spring Boot

## Descripción

API REST de ejemplo para gestión de cuentas bancarias. Permite:

* Crear cuentas.
* Consultar cuentas y sus movimientos.
* Depositar y retirar dinero.
* Validación de saldo (no se puede dejar en negativo).
* Autenticación simple con token en cabecera.

**Versionado:** `/v1/...`

---

## Requisitos

* Java 17 o superior
* Maven
* Eclipse, IntelliJ o cualquier IDE compatible con Spring Boot

---

## Levantar la aplicación

1. Clonar el repositorio:

```bash
git clone <URL_DEL_REPO>
cd <NOMBRE_DEL_PROYECTO>
```

2. Levantar con Maven:

```bash
mvn spring-boot:run
```

La API correrá en: `http://localhost:8080`

---

## Autenticación

Todas las peticiones requieren la cabecera `Authorization` con token válido:

```
Authorization: Bearer 12345ABC
```

* Si el token falta o es incorrecto → `401 Unauthorized`

---

## Endpoints

### 1. Crear cuenta

```
POST /v1/accounts?number=123&initialBalance=100
```

### 2. Depositar saldo

```
POST /v1/accounts/123/deposit?amount=50
```

### 3. Retirar saldo

```
POST /v1/accounts/123/withdraw?amount=20
```

### 4. Consultar cuenta

```
GET /v1/accounts/123
```

### 5. Consultar movimientos

```
GET /v1/accounts/123/movements
```

---

## Ejemplo de uso en Postman

**Headers:**

```
Authorization: Bearer 12345ABC
```

**GET movimientos:**

```
GET http://localhost:8080/v1/accounts/123/movements
```

**POST depositar:**

```
POST http://localhost:8080/v1/accounts/123/deposit?amount=50
```

---

## Tests

Tests unitarios de `AccountService` incluidos en `src/test/java/com/bank/bankapi/AccountServiceTest.java`. Se pueden ejecutar con Maven:

```bash
mvn test
```

* Los tests cubren:

  * Creación de cuenta y depósito inicial.
  * Depositar saldo adicional.
  * Retirar saldo correctamente.
  * Intentar retirar más saldo del disponible (lanza `ConflictException`).

---

## Notas adicionales:

* La API está implementada con almacenamiento en memoria (`HashMap`).
* La versión de la API se indica en la URL (`/v1/`).
* El token de autenticación es fijo para la prueba técnica y solo sirve para validación básica.

