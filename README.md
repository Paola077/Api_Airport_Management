![CI](https://github.com/Paola077/Api_Airport_Management/actions/workflows/ci.yml/badge.svg)

# ✈️ Airline Management System

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.1-green?style=for-the-badge&logo=spring) ![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16.2-blue?style=for-the-badge&logo=postgresql) ![Maven](https://img.shields.io/badge/Maven-4.0.0-red?style=for-the-badge&logo=apachemaven)

## 📌 Descripción
Sistema de gestión para una aerolínea desarrollado con **Spring Boot** y **Spring Security**. Permite administrar usuarios, vuelos, reservas y aeropuertos con autenticación segura a través de **Basic Auth**. Además, automatiza la actualización de vuelos y reservas.

## 🚀 Tecnologías Utilizadas
- **Java 21**
- **Spring Boot**
- **Spring Security**
- **PostgreSQL**
- **Maven**
- **JWT para seguridad**

### 🔗**Diagrama De Base de datos**: [UML](https://lucid.app/lucidchart/82c343a9-4c3b-44da-ac04-dba8b38c3f40/edit?beaconFlowId=4DCC666723C27FCE&invitationId=inv_1a06f0fb-a03c-4081-9f20-28b6522ffd9d&page=0_0#)

## 📜 Características
✅ **Gestión de Usuarios**: Registro, autenticación, roles (**ROLE_ADMIN** y **ROLE_USER**).

✅ **Basic Auth**: Sesiones seguras.  

✅ **Gestión de Vuelos**: CRUD de vuelos, con restricción de selección para vuelos sin plazas o vencidos. 

✅ **Reservas Inteligentes**: Confirmación y expiración automática de reservas en 15 minutos.  

✅ **Automatización con @Scheduled**:
- Limpieza de reservas expiradas.
- Eliminación de reservas confirmadas en vuelos pasados.
- Activación/desactivación de vuelos según disponibilidad y fecha.

✅ **Búsqueda Avanzada**: Filtrado por estado, aeropuerto de origen/destino, fechas y horarios.

✅ **Historial de Reservas**: Consulta de reservas de cada usuario.

✅ **Excepciones Personalizadas**: Manejo eficiente de errores.

✅ **Pruebas Automáticas**: Configuración de CI con tests en `main`.

## 👨‍💻 Roles y Permisos
| Acción | ROLE_ADMIN | ROLE_USER |
|--------|-----------|-----------|
| CRUD Aeropuertos | ✅ | ❌ |
| CRUD Vuelos | ✅ | ❌ |
| Listado de Reservas | ✅ | ❌ |
| Ver Historial de Reservas | ✅ | ✅ |
| Crear y Confirmar Reservas | ✅ | ✅ |

## 🔧 Instalación
1️⃣ Clona el repositorio:
```sh
 git clone https://github.com/Paola007/Api_Airport_Management.git
```
2️⃣ Configura la base de datos PostgreSQL en `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/airport_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```
3️⃣ Ejecuta el proyecto con Maven:
```sh
mvn spring-boot:run
```

## 📜 API Endpoints
### 🧑‍💻 Usuarios
| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `POST` | `/api/register` | Registro de usuario |
| `POST` | `/api/login` | Inicio de sesión |
| `GET` | `/api/users` | Obtener todos los usuarios |
| `PUT` | `/api/users/update-role?email={email}&newRole={role}` | Actualizar rol de usuario |
| `DELETE` | `/api/user/{id}` | Eliminar usuario |

### 🏢 Aeropuertos
| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `POST` | `/airports` | Crear aeropuerto |
| `GET` | `/airports` | Obtener todos los aeropuertos |
| `GET` | `/airports/{id}` | Obtener aeropuerto por ID |
| `PUT` | `/airports/{id}` | Actualizar aeropuerto |
| `DELETE` | `/airports/{id}` | Eliminar aeropuerto |

### ✈️ Vuelos
| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `POST` | `/flights` | Crear vuelo |
| `GET` | `/flights` | Obtener vuelos disponibles |
| `GET` | `/flights/{id}` | Obtener vuelo por ID |
| `PUT` | `/flights/{id}` | Actualizar vuelo |
| `DELETE` | `/flights/{id}` | Eliminar vuelo |

### 📝 Reservas
| Método | Endpoint                    | Descripción                        |
|--------|-----------------------------|------------------------------------|
| `POST` | `/api/reservations`         | Crear una reserva                  |
| `POST` | `/api/reservations/confirm`     | Confirmar reserva                  |
| `GET` | `/api/reservations/flight/{id}` | Obtener reservas por ID de vuelo   |
| `GET` | `/api/reservations/user/{id}`   | Obtener reservas por ID de usuario |
| `GET` | `/api/reservations`             | Obtener todas las reservas         |
| `GET` | `/api/reservations/{id}`        | Obtener reserva por ID de reserva  |
| `DELETE` | `/api/reservations/{id}`        | Eliminar reserva                   |

### 📇 Perfiles
| Método   | Endpoint                                            | Descripción                       |
|----------|-----------------------------------------------------|-----------------------------------|
| `GET`    | `/api/profile`                                      | Obtener todos los perfiles        |
| `GET`    | `/api/profile/{id}`                                 | Obtener perfil por ID             |
| `GET`    | `/api/profile/search/?email=example123@example.com` | Obtener perfil por email          |
| `GET`    | `/api/profile/user/{id}`                            | Obtener perfil ID de usuario      |


## 📬 Contacto
🔗 **LinkedIn**: [LinkedIn](https://www.linkedin.com/in/paolaperdomo07/)  
🚀 **Portafolio**: [Github](https://github.com/Paola077)

