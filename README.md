# ATuServicio

## CS 2031 Desarrollo Basado en Plataformas

![Descripción de la imagen](https://lh3.googleusercontent.com/XjT_KCRn096aRrRrR_scjHHW7K7rEjAl-0mOgW2RL13LTKUattb3EfV1uEKJiKIG_rNXx310vfURFn7EcULm6q0=w1280)

### Integrantes
- Sebastian Antonio Hernandez Miñano
- Henry Rutber Quispe Suta
- Andres Martin Benjamin Torres Ramos
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
- `Long id_user` (Primary Key)
- `LocalDateTime registration_date`
- `String email`
- `String password`
- `String phoneNumber`
- `String address`

**Relaciones**:
Relación one-to-one con **Location**.
Relación de herencia con las entidades **Provider** y **Client**.

#### Provider
**Descripción**:
Subclase de **User**
Representa a los proveedores de los diferentes servicios dentro de la aplicación.

**Atributos**:
- `Boolean active`
- `Integer rating`
- `Double comission`
- `String ruc`

**Relaciones**:
Relación many-to-many con **ServiceEntity**.
Relación de herencia con las entidades **Freelancer** y **Enterprise**.

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

#### Enterprise
**Descripción**:
Subclase de **Provider**
Representa a una empresa que ofrece servicios.

**Atributos**:
- `String name`
- `Category category` (ENUM)
- `Size size` (ENUM)

#### Client
**Descripción**:
Subclase de **User**
Define a los clientes que solicitan servicios en la plataforma.

**Atributos**:
- `Long dni`
- `Boolean showAds`

#### Location
**Descripción**:
Define la ubicación del usuario.

**Atributos**:
- `Long id_location` (Primary Key)
- `String description`
- `String coordinates`

#### Arrangement
**Descripción**:
Registra el acuerdo entre un proveedor y un cliente para un servicio.

**Atributos**:
- `Long id_procedure` (Primary Key)
- `LocalDateTime start_date`
- `LocalDateTime end_date`
- `String location`
- `Long id_payment`

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
- `LocalDateTime date`
- `String comment`
- `Boolean edited`

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
