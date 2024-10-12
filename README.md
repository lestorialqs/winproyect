# ATuServicio

## CS 2031 Desarrollo Basado en Plataformas

![Descripción de la imagen](https://lh3.googleusercontent.com/LDCxZNaQyYHPSnggss4PuX6dUCnletxobqQ0un_x1mZv_5s8uX9OT1TwkRJqvBZY8cfjflovHe1ChiPsWFTI3KU=w1280)


### Integrantes
- Sebastian Antonio Hernandez Miñano
- Henry Rutber Quispe Sutta
- Andrés Martín Benjamín Torres Ramos
- Luis Javier Millones Carrasco

## Índice

1. [**Introducción**](##Introducción)
    - [Contexto](###Contexto)
    - [Objetivos del proyecto](###Objetivos-del-proyecto)
2. [**Problema**](#problema)
    - [Descripción del problema](#descripción-del-problema)
    - [Justificación](#justificación)
3. [**Descripción de la solución**](#descripción-de-la-solución)
    - [Funcionalidades Implementadas](#funcionalidades-implementadas)
    - [Tecnologías implementadas](#tecnologías-implementadas)
4. [**Modelado de Entidades**](#modelado-de-entidades)
    - [Diagrama de entidades](#diagrama-de-entidades)
    - [Descripción de entidades](#descripción-de-entidades)
        - [User](#user)
        - [Provider](#provider)
        - [Freelancer](#freelancer)
        - [Enterprise](#enterprise)
        - [Client](#client)
        - [Location](#location)
        - [Arrangement](#arrangement)
        - [Payment](#payment)
        - [ServiceEntity](#serviceentity)
        - [Review](#review)
5. [**Testing y Manejo de Errores**](#testing-y-manejo-de-errores)
    - [Niveles de Testing Realizados](#niveles-de-testing-realizados)
    - [Resultados](#resultados)
    - [Manejo de errores](#manejo-de-errores)
6. [**Medidas de Seguridad Implementadas**](#medidas-de-seguridad-implementadas)
    - [Seguridad de Datos](#seguridad-de-datos)
    - [Prevención de Vulnerabilidades](#prevención-de-vulnerabilidades)
7. [**Eventos y Asincronía**](#eventos-y-asincronía)
8. [**GitHub**](#github)
9. [**Conclusiones**](#conclusiones)
10. [**Apéndices**](#apéndices)

## Introducción

### Contexto
ATuServicio es una plataforma innovadora que permite a los usuarios solicitar servicios profesionales y técnicos de manera rápida y eficiente, disponible las 24 horas del día, los 7 días de la semana. Inspirada en la funcionalidad de plataformas como Uber, ATuServicio conecta a los clientes con una amplia gama de profesionales y técnicos, desde técnicos de reparación de autos y gasfiteros hasta veterinarios y servicios de comida.

### Objetivos del proyecto
- Incorporar funcionalidades que permitan la negociación de precios entre usuarios y proveedores, junto con un sistema de mapa para visualizar la disponibilidad de proveedores en tiempo real.
- Lanzar la plataforma 24/7 con múltiples categorías de servicios, permitiendo a los usuarios solicitar servicios en cualquier momento, con al menos 10 categorías diferentes como veterinaria, mecánica, gasfitería y comida.
- Garantizar conexión rápida con proveedores, asegurando que los usuarios puedan conectarse con un proveedor de servicios en menos de 5 minutos desde la solicitud.

## Problema

### Descripción del problema
Las personas enfrentan situaciones inesperadas que requieren servicios profesionales inmediatos, como fugas de agua, desperfectos mecánicos o atención médica urgente, pero muchos servicios no están disponibles las 24 horas o implican largas esperas. Además, encontrar profesionales confiables y negociar precios puede ser estresante en momentos críticos.

Por otro lado, muchas microempresas y freelancers carecen de una plataforma centralizada para ofrecer sus servicios, lo que limita su visibilidad y crecimiento en el mercado, dificultando la competencia con empresas más grandes y especializadas.

### Justificación
Abordar este problema es esencial para mejorar el acceso a servicios profesionales en emergencias, reduciendo el estrés de los usuarios. Además, brinda a microempresas y freelancers una plataforma que fomenta su crecimiento y competitividad, lo cual enriquece también la economía local al diversificar las opciones disponibles para los consumidores.

## Descripción de la solución

### Funcionalidades Implementadas

Para resolver la necesidad de una plataforma eficiente que conecte usuarios con servicios profesionales, hemos implementado **ATuServicio**, una aplicación web y móvil diseñada para facilitar el contacto rápido y efectivo entre personas y empresas. La aplicación permite cerrar acuerdos de manera flexible y eficiente, ofreciendo soluciones rápidas para problemas cotidianos. A continuación, se describen las principales funcionalidades implementadas:

#### Para los Usuarios
- **Registro de usuarios**: Los usuarios pueden crear fácilmente una cuenta mediante su correo electrónico o número de teléfono, proporcionando detalles básicos como dirección y método de pago.
- **Visualización en tiempo real de empresas cercanas**: Los usuarios pueden visualizar, en un mapa interactivo, las empresas o freelancers disponibles en su ubicación, permitiendo seleccionar proveedores de servicios cercanos y de rápida respuesta.
- **Pago a través de la aplicación**: Integración con pasarelas de pago seguras que permiten a los usuarios pagar directamente desde la aplicación, facilitando un proceso de pago rápido y sin complicaciones.
- **Sistema de calificación del servicio**: Después de cada servicio, los usuarios pueden dejar una calificación y comentarios sobre la experiencia, ayudando a mejorar la calidad de los proveedores y guiar a futuros usuarios.
- **Versión sin anuncios**: Los usuarios pueden optar por una versión premium de **ATuServicio** sin publicidad, lo que les brinda una experiencia más fluida y sin distracciones.

#### Para los Proveedores
- **Registro como empresa o freelancer**: Los proveedores pueden registrarse en la plataforma tanto como una empresa formal o como freelancers independientes, adaptándose a diferentes tipos de profesionales y negocios.
- **Cobro de comisión basado en usuarios alcanzados**: Los proveedores pagan una comisión basada en la cantidad de usuarios que visualizan o interactúan con sus servicios, lo que fomenta el crecimiento del negocio y maximiza su exposición.
- **Personalización de los servicios ofrecidos**: Los proveedores pueden personalizar sus servicios, ajustando detalles como descripciones, precios sugeridos, ubicaciones de operación y horarios de disponibilidad, para adaptarse mejor a la demanda y necesidades del mercado.


#### Para los provedores
- **Registro como empresa o como freelancer**
- **Cobro de comisión según la cantidad de usuarios alcanzados**
- **Posibilidad de personalizar los servicios que ofrecen**

#### Generales
- **Permitir la negociación del precio final por el producto**
- **Geolocalización por medio de la Api de Google Maps**


### Tecnologías implementadas
Para el desarrollo de **ATuServicio**, utilizaremos **IntelliJ** como entorno de desarrollo integrado (IDE) debido a la amplia variedad de herramientas que ofrece para trabajar con Java y Spring Boot.

El backend de la aplicación se desarrollará en **Java**, utilizando el framework **Spring** por su facilidad de integración con herramientas como **Tomcat** y **Docker**, su amplio uso en aplicaciones profesionales y su sólida comunidad, que proporciona documentación y soluciones a posibles problemas.

Además, utilizaremos **PostgreSQL** ejecutándose en **Docker** para la gestión de la base de datos.

Por último, realizaremos el despliegue en **Amazon Web Services (AWS)**.

## Modelado de entidades

### Diagrama de entidades
_Aquí va el diagrama entidad-relación_

### Descripción de entidades

#### User
**Descripción**:  
Representa a los usuarios que pueden solicitar servicios en la plataforma.

**Atributos**:
- `Long id` (Primary Key)
- `LocalDateTime registration_date`
- `String email`
- `String password`
- `String phoneNumber`
- `String address`
- `Rol rol` (Enum)

**Relaciones**:  
Relación one-to-one con **Location**.  
Relación de herencia con las entidades **Provider** y **Client**.

**DTOs:**

UserLoginDto:
- `String email`
- `String password`


#### Provider
**Descripción**:  
Subclase de **User**  
Representa a los proveedores de los diferentes servicios dentro de la aplicación.

**Atributos**:
- `Boolean estate`
- `Integer rating`
- `Double comission`
- `String ruc`

**Relaciones**:  
Relación many-to-many con **ServiceEntity**.  
Relación de herencia con las entidades **Freelancer** y **Enterprise**.



#### Client
**Descripción**:  
Subclase de **User**  
Define a los clientes que solicitan servicios en la plataforma.

**Atributos**:
- `Long dni`
- `String first_name`
- `String last_name`
- `Boolean showAds`


**DTOs:**

ClientDtoViewPerfilResponse:
- `String first_name`
- `String last_name`
- `String phoneNumber`
- `String address`
- `String email`

ClientDtoRegister:
- `String first_name`
- `String last_name`
- `String phoneNumber`
- `String address`
- `String email`
- `String password`
- `Long DNI` (Opcional)



#### Freelancer
**Descripción**:  
Subclase de **Provider**  
Representa a un proveedor autónomo.

**Atributos**:
- `String first_name`
- `String last_name`
- `String speciality`
- `String experience`
- `String grade`
- `Long dni`

**DTOs:**

FreelancerDtoRegister:
- `String first_name`
- `String last_name`
- `Long phoneNumber`
- `String address`
- `String password`
- `String email`
- `Long dni`

FreelancerDtoUpdateProfile:
- `String first_name`
- `String last_name`
- `Integer age`
- `Long phoneNumber`
- `String address`
- `Long dni`
- `String email`
- `LevelEducation levelEducation`
- `String experience`
- `String speciality`

FreelancerDtoViewPerfilResponse:
- `String first_name`
- `String last_name`
- `String phoneNumber`
- `String address`
- `String email`
- `Long dni`
- `LevelEducation levelEducation`
- `String experience`
- `String speciality`


#### Enterprise
**Descripción**:  
Subclase de **Provider**  
Representa a una empresa que ofrece servicios.

**Atributos**:
- `String name`
- `String description`
- `Long ruc`
- `Size size` (ENUM)
- `BusinessSector businessSector` (ENUM)
- `String address`

**DTOs:**

EnterpriseDtoRegister:
- `Long ruc`
- `String password`
- `String email`
- `String address`
- `Size size` (ENUM)
- `BusinessSector businessSector` (ENUM)

EnterpriseDtoUpdateProfile:
- `Long ruc`
- `String name`
- `String description`
- `String email`
- `String address`
- `Size size` (ENUM)
- `BusinessSector businessSector` (ENUM)

EnterpriseDtoViewPerfilResponse:
- `Long ruc`
- `String name`
- `String description`
- `String email`
- `String address`
- `Size size` (ENUM)
- `BusinessSector businessSector` (ENUM)

#### Location
**Descripción**:  
Define la ubicación del usuario.

**Atributos**:
- `Long id_location` (Primary Key)
- `String description`
- `String coordinates`

**DTOs:**

LocationResponseDto:
- `String coordinates`

#### Arrangement
**Descripción**:  
Registra el acuerdo entre un proveedor y un cliente para un servicio.

**Atributos**:
- `Long id_procedure` (Primary Key)
- `LocalDateTime start_date`
- `LocalDateTime end_date`
- `String location`
- `Double agreed_amount`
- `Long id_payment`

**DTOs:**
LocationResponseDto:
- `LocalDateTime start_date`
- `LocalDateTime end_date`
- `String location`
- `Double agreed_amount`

#### Payment
**Descripción**:  
Almacena la información del pago realizado por un servicio.

**Atributos**:
- `Long id_payment` (Primary Key)
- `Double amount`
- `String method`
- `LocalDateTime date`

#### ServiceEntity
**Descripción**:  
Define los servicios ofrecidos por los providers dentro de la plataforma.

**Atributos**:
- `Long id_service_entity` (Primary Key)
- `String description`
- `Double suggested_price`
- `String name`
- `String location`
- `String tag`

#### Review
**Descripción**:  
Registra las calificaciones y comentarios de los usuarios sobre los servicios recibidos.

**Atributos**:
- `Long id_review` (Primary Key)
- `Integer rating`
- `Date date`
- `String comment`
- `Boolean edited`

**Relaciones**:  
Relación many-to-one con **ServiceEntity**.  
Relación de herencia con las entidades **Freelancer** y **Enterprise**.

**DTOs:**

ReviewRequestDto:
- `Integer rating`
- `String comment`
- `Long serviceId`
- `Long userId`

## Testing y Manejo de Errores

### Niveles de Testing Realizados

### Resultados

### Manejo de errores

## Medidas de Seguridad Implementadas

### Seguridad de Datos

### Prevención de Vulnerabilidades

## Eventos y Asincronía

## GitHub

## Conclusiones

## Apéndices


# Endpoints para ATuServicio



## Endpoints para User

## 1. Registro de Usuario
- **POST** /user/register
    - **Descripción**: Selecciona si eres Client o Provider.
    - **Cuerpo**:
      ```json
      {
        "type": "ENUM"  
      }
      ```


# Endpoints para Client

## 1. Registro de Clientes
- **POST** /client/register
    - **Descripción**: Permite a un nuevo usuario registrarse en la plataforma.
    - **Cuerpo**:
      ```json
      {
        "email": "string",
        "password": "string",
        "phoneNumber": "string",
        "address": "string"
      }
      ```

## 2. Iniciar Sesión de Clientes
- **POST** /client/login
    - **Descripción**: Permite a un usuario iniciar sesión en la plataforma.
    - **Cuerpo**:
      ```json
      {
        "email": "string",
        "password": "string"
      }
      ```

## 3. Obtener Información de un Cliente
- **GET** /client/{id}
    - **Descripción**: Recupera la información del usuario especificado.
    - **Cuerpo**:
      ```json
      {
        "firsname": "string",
        "lastname": "string",
        "dni": "string",
        "phonenumber": "string",
        "email": "string"
      }
      ```

## 6. Obtener Historial de Servicios
- **GET** /client/{id}/arrangements
    - **Descripción**: Recupera el historial de servicios solicitados por un cliente.

---

# Endpoints para Proveedores

## 1. Registro de Proveedor
- **POST** /provider/register
    - **Descripción**: Permite a un proveedor registrarse como empresa o freelancer.
    - **Cuerpo**:
      ```json
      {
        "category": "enum",
      }
      ```


## 3. Obtener Proveedores Cercanos
- **GET** /provider/nearby?lat={latitude}&lng={longitude}
    - **Descripción**: Obtiene una lista de proveedores cercanos a una ubicación dada.

---


# Endpoints para Freelancer
## 1. Registro de un freelancer

- **POST** /provider/freelancer/register
- **Descripción**: Permite a un proveedor registrarse freelancer.
    - **Cuerpo**:
      ```json
      {
        "firstName": "string",
        "lastName": "string",
        "age": "string",
        "speciality": "string",
        "experience": "string",
        "grade": "string",
      }


# Endpoints para Servicios

## 1. Solicitar Servicio
- **POST** /serviceEntity/solicitate
    - **Descripción**: Permite a un cliente solicitar un servicio.
    - **Cuerpo**:
      ```json
      {
        "providerId": "long",
        "serviceId": "long",
        "location": "string",
        "startDate": "datetime",
        "endDate": "datetime"
      }
      ```

## 2. Publicar un Servicio
- **POST** /serviceEntity/publish
    - **Descripción**: Permite a un proveedor publicar un servicio.
    - **Cuerpo**:
      ```json
      {
        "descripcion": "string",
        "nombre": "string",
        "tags": "string",
        "precioSugerido": "decimal",
        "location": "string"
      }
      ```

## 3. Obtener un Servicio
- **GET** /serviceEntity/{id}
    - **Descripción**: Obtiene los detalles de un servicio.
    - **Respuesta**:
      ```json
      {
        "descripcion": "string",
        "nombre": "string",
        "tags": "string",
        "location": "string",
        "rating": "integer",
        "provider": "string"
      }
      ```

## 4. Borrar un Servicio
- **DELETE** /serviceEntity/{id}
    - **Cuerpo**:
      ```json
      {
        "id": "1"
      }
      ```

## 5. Actualizar un Servicio
- **PATCH** /serviceEntity/{id}
    - **Cuerpo**:
      ```json
      {
        "update1": "value",
        "update2": "value"
      }
      ```

# Endpoints para Calificaciones

## 1. Agregar Reseña

- **POST** /serviceEntity/{id}/review
    - **Descripción**: Permite a un usuario dejar una reseña sobre un servicio.
    - **Cuerpo**:
      ```json
      {
        "rating": "integer",
        "comment": "string",
        "userId": "long"
      }
      ```

## 2. Obtener Reseñas por Servicio
- **GET** /serviceEntity/{id}/review
    - **Descripción**: Recupera todas las reseñas asociadas a un servicio específico.
    - **Respuesta** (Lista):
      ```json
      [
        {
          "edited": "bool",
          "rating": "integer",
          "comment": "string",
          "userId": "long",
          "date": "LocalDateTime"
        }
      ]
      ```

## 3. Editar Reseña por Id
- **PATCH** /serviceEntity/{Id}/reviews/{id}
    - **Descripción**: Permite editar una reseña específica.
    - **Cuerpo**:
      ```json
      {
        "rating": "integer",
        "comment": "string"
      }
      ```

## 4. Eliminar Reseña por Id
- **DELETE** /serviceEntity/{Id}/reviews/{id}
    - **Descripción**: Permite eliminar una reseña específica.
      ```json
      {
        "reviewId": "long"
      }
      ```


---

# Endpoints para Pagos

## 1. Realizar Pago dentro de la aplicación
- **POST** /payment
    - **Descripción**: Permite a un cliente realizar un pago por un servicio.
    - **Cuerpo**:
      ```json
      {
        "amount": "double",
        "method": "string",
        "date": "datetime",
        "arrangementId": "long"
      }
      ```

## 2. Obtener Detalles de Pago
- **GET** /payment/{id}
    - **Descripción**: Recupera la información del pago especificado.

---


# Endopoints de Arrangement
## 1. Postear un arrangement (hacer un contrato con una empresa)
- **POST**
  DESCRIPCION: solicitar un contrato desde cliente
    - client_id
    - serviceEntity_id

## 2. DELETE ARRANGEMENTE
- **DELETE**
  - 

# Endpoints de Geolocalización

## 1. Obtener Ubicación Actual
- **GET** /location/current
    - **Descripción**: Permite al usuario obtener su ubicación actual.
      ```json
      {
        "userId": "long"
      }
      ```
