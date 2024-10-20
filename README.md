# ATuServicio

IP al Proyecto en AWS: 44.213.59.93

## CS 2031 Desarrollo Basado en Plataformas

### Integrantes
- Sebastian Antonio Hernandez Miñano (202320043)
- Henry Rutber Quispe Sutta (202320078)
- Andrés Martín Benjamín Torres Ramos (202320163)
- Luis Javier Millones Carrasco (202320115)

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
    - [GitHub Action](#GitHub-Actions)
    - [GitHub Projects](#GitHub-projects)
10. [**Conclusiones**](#conclusiones)
11. [**Apéndices**](#apéndices)

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
- **Geolocalización por medio de la API de Google Maps**


### Tecnologías implementadas
Para el desarrollo de **ATuServicio**, utilizaremos **IntelliJ** como IDE, aprovechando sus herramientas para Java y Spring Boot.

El backend se desarrollará en **Java** con el framework **Spring**, facilitando la integración con **Tomcat** y **Docker**, y beneficiándonos de su amplia comunidad y documentación.

Usaremos una imagen de **PostgreSQL** en **Docker** para la gestión de la base de datos.

Además, implementaremos **WebSockets** para el **chat en vivo**, y utilizaremos el **API de Reniec** para validar **RUC** y **DNI**. También integraremos el **API de Google Maps** para obtener direcciones y enlaces de ubicación.

Finalmente, el despliegue se realizará en **Amazon Web Services (AWS)**, aprovechando su infraestructura escalable.



## Modelado de entidades

### Diagrama de entidades

![diagrama de flujo](https://hackmd.io/_uploads/BJg5sgc1Jx.png)

### Descripción de entidades

#### User
**Descripción**:
Representa a los usuarios que pueden solicitar servicios en la plataforma.

**Atributos**:
- `Long id` (Primary Key)
- `LocalDateTime registrationDate`
- `String email`
- `String password`
- `String phoneNumber`
- `String address`
- `Role role` (Enum)

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
- `Long ruc`
- `Boolean estate`
- `Float rating`
- `Double comission`


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

ClientUpdateDto:
- `String first_name`
- `String last_name`
- `String phoneNumber`
- `String address`
- `String email`
- `String dni`

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
- `String firstName`
- `String lastName`
- `String experience`
- `String levelEducation`
- `Integer dni`
- `Integer age`

**DTOs:**

FreelancerDtoRegister:
- `String first_name`
- `String last_name`
- `Long phoneNumber`
- `String address`
- `String password`
- `String email`
- `Long dni`

FreelancerUpdateDto:
- `String first_name`
- `String last_name`
- `Integer age`
- `Integer dni`
- `LevelEducation levelEducation`
- `String experience`
- `Long phoneNumber`
- `String address`
- `String email`


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
- `Long ruc`
- `String name`
- `String description`
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
- `String name`
- `String description`
- `Size size` (ENUM)
- `BusinessSector businessSector` (ENUM)
- `Long phoneNumber`
- `String address`
- `String email`


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
- `Long idLocation` (Primary Key)
- `String address`
- `Double latitude`
- `Double longitude`

**DTOs:**

LocationResponseDto:
- `String formattedAddress`
- `Double latitude`
- `Double longitude`
- `String message`
- `String status`
- `String googleMapsLink`

#### Arrangement
**Descripción**:
Registra el acuerdo entre un proveedor y un cliente para un servicio.

**Atributos**:
- `Long id` (Primary Key)
- `LocalDateTime date`
- `String Status`
- `Float price`

**DTOs:**
ArragementResponseDto:
- `String FirstName`
- `String LastName`
- `String PhoneNumber`
- `Double String email`

ArrangementRequestDto:
- `Float price`

**Relaciones**:
Relación many-to-one con **Client**.
Relación many-to-one con **ServiceEntity**.

#### Payment
**Descripción**:
Almacena la información del pago realizado por un servicio.

**Atributos**:
- `Long id_payment` (Primary Key)
- `Double amount`
- `String method`
- `String currency`
- `LocalDateTime date`

**DTOs:**
PaymentRequestDto:
- `Double amount`
- `String currency`
- `Method method`(Enum)


**Relaciones**:
Relación one-to-one con **Arrangement**.


#### ServiceEntity
**Descripción**:
Define los servicios ofrecidos por los providers dentro de la plataforma.

**Atributos**:
- `Long id_service_entity` (Primary Key)
- `String description`
- `Double suggested_price`
- `String name`
- `String location`
- `String address`
- `Tag tags` (Lista)

**DTOs:**
ServiceDtoRequest:
- `String description`
- `String name`
- `String address`
- `Double suggestedPrice`
- `Tag tags` (Lista)

ServiceDtoResponse:
- `String name`
- `String nameProvider`
- `String description`
- `String address`
- `Double suggestedPrice`
- `Float avg_rating`
- `Page<Review> pageableReviews`
- `Tag tags` (Lista)


**Relaciones**:
Relación many-to-one con **Provider**.



#### Review
**Descripción**:
Registra las calificaciones y comentarios de los usuarios sobre los servicios recibidos.

**Atributos**:
- `Long id_review` (Primary Key)
- `Integer rating`
- `ZonedDateTime date`
- `String comment`
- `Boolean edited`

**DTOs:**

ReviewDtoCreateRequest
- `Integer rating`
- `String comment`
- `Long userId`

ReviewEditRequestDto:
- `Integer rating`
- `String comment`

**Relaciones**:
Relación many-to-one con **ServiceEntity**.
Relación many-to-one con **Client**.


## Testing y Manejo de Errores

### Niveles de Testing Realizados

Niveles de Testing Realizados
En el desarrollo del proyecto, se han llevado a cabo diversos niveles de testing para garantizar la calidad y robustez del software. A continuación, se detallan los niveles de testing implementados:

### Tests Realizados

Sea realizó los tests usando el framework de Mockito para el testing en el Service Layer de las entidades más importantes y Test containers para el test de Repositorio.


A continuación imágenes de tests en las dos capas:
![image](https://hackmd.io/_uploads/rJS8cbc11l.png)
![image](https://hackmd.io/_uploads/Sk-wqWcyJg.png)


### TESTS EN EJECUCIÓN:
A continuación imágenes de tests en ejecución:
![image](https://hackmd.io/_uploads/BJZs9Zc1Jx.png)
![image](https://hackmd.io/_uploads/Hkon5-5kyl.png)
![image](https://hackmd.io/_uploads/rJoa5bqJ1g.png)
![image](https://hackmd.io/_uploads/SkylsW5kJx.png)
![image](https://hackmd.io/_uploads/ryW4sbcJkl.png)
![image](https://hackmd.io/_uploads/Bk_wiW9yyl.png)
![image](https://hackmd.io/_uploads/BJUYib51yl.png)
![image](https://hackmd.io/_uploads/S1psjZqyyx.png)
![image](https://hackmd.io/_uploads/BJoTiZc11l.png)


### Manejo de errores
El manejo de errores es crucial para proporcionar una experiencia de usuario fluida y confiable. En el proyecto, se han implementado diversas estrategias para gestionar errores y excepciones:

1. Excepciones Personalizadas
   Se han definido excepciones personalizadas para manejar casos de error específicos en el negocio, como EntityNotFoundException o InvalidInputException. Estas excepciones permiten capturar y manejar errores de manera controlada y proporcionar mensajes de error significativos al usuario.
## Medidas de Seguridad Implementadas

### Seguridad de Datos
- **Autenticación basada en tokens JWT:**
  En **ATuServicio**, implementamos un sistema de autenticación que utiliza JSON Web Tokens (JWT) para verificar la identidad de los usuarios. Al momento de autenticar, se genera y se envía un token JWT en el encabezado de autorización de la solicitud HTTP. Este token es validado para garantizar que el acceso sea seguro.

- **Filtro de autenticación personalizado:**
  Desarrollamos un filtro de autenticación personalizado (**JwtAuthenticationFilter**), que se ejecuta para cada solicitud HTTP. Este filtro extrae el token JWT del encabezado de autorización y verifica su validez, lo que permite gestionar la autenticación en el contexto de seguridad de la aplicación.

- **Seguridad a nivel de método:**
  Utilizamos la anotación **@EnableMethodSecurity** en **ATuServicio** para implementar seguridad a nivel de método. Esto permite proteger los métodos individuales en las clases de servicio, asegurando que se respeten las restricciones de acceso establecidas.

- **Política de creación de sesión sin estado:**
  La configuración de seguridad de **ATuServicio** establece que el sistema opere bajo una política de creación de sesión sin estado. Esto significa que no se almacenarán sesiones en el servidor; en su lugar, cada solicitud se autenticará de manera independiente utilizando el token JWT proporcionado.

- **Control de acceso basado en roles:**
  Implementamos un sistema de control de acceso que permite la reclamación de roles dentro del servicio. Esto significa que los permisos se asignan a los usuarios de acuerdo a su rol, garantizando que solo puedan acceder a las funcionalidades que les corresponden.

### Prevención de Vulnerabilidades
- **Prevención de inyección SQL:**
  En **ATuServicio**, se emplean prácticas para prevenir la inyección SQL mediante el uso de JPA y consultas preparadas. Esto asegura que los parámetros de las consultas se gestionen de forma segura y no se vean comprometidos.

- **Prevención de ataques XSS:**
  Se implementaron medidas en **ATuServicio** para proteger contra ataques de tipo Cross-Site Scripting (XSS). Spring Boot proporciona soporte para mitigar estos riesgos a través de la configuración adecuada y el uso de plantillas seguras.

- **Prevención de falsificación de solicitudes entre sitios (CSRF):**
  Se desactivó la protección CSRF en **ATuServicio**, dado que el acceso se realiza a través de una API RESTful. Se espera que los clientes sean aplicaciones de terceros, no navegadores, y se ha diseñado la API para facilitar este tipo de acceso de manera segura.

## Eventos y Asincronía

En **ATuServicio**, se implementaron diversas estrategias de asincronía para mejorar la experiencia del usuario:

- **API de Maps:** Se utiliza la API de Maps para gestionar ubicaciones de manera eficiente. Las solicitudes a esta API se manejan de forma asíncrona, permitiendo que la aplicación continúe funcionando mientras se obtienen los datos de ubicación.

- **Verificación de DNI y RUC:** La validación de DNI y RUC a través de las APIs de **RENIEC** y **SUNAT** se realiza de manera asíncrona, asegurando que el proceso de autenticación no bloquee la interfaz de usuario y se mantenga ágil.

- **Chat en tiempo real con Websockets:** La implementación de Websockets permite la comunicación en tiempo real entre los usuarios. Esto facilita un chat en vivo eficiente, donde los mensajes se envían y reciben instantáneamente, mejorando la interacción entre los clientes y proveedores de servicios.


## GitHub

### GitHub Projects

Durante el desarrollo de **ATuServicio**, utilizamos **GitHub Projects** para gestionar y organizar nuestras tareas de manera eficiente. A continuación se detallan las principales características y prácticas que implementamos:

- **Asignación de Issues:** Creamos **issues** para cada tarea, bug o mejora necesaria. Cada miembro del equipo era responsable de asignarse los issues correspondientes, lo que facilitó la distribución del trabajo y la responsabilidad individual.

- **Deadlines:** Establecimos **deadlines** para cada issue, asegurando que todas las tareas se completaran dentro de un marco temporal específico. Esto nos permitió mantener un ritmo de trabajo constante y cumplir con los plazos del proyecto.


### GitHub Actions

Para la automatización de tareas y la mejora del flujo de trabajo, implementamos **GitHub Actions** en nuestro proyecto. A continuación se describen los flujos que configuramos:

- **Integración Continua (CI):** Configuramos acciones para ejecutar pruebas automáticamente cada vez que se realizaba un **push** a la rama principal. Esto garantizó que el código comprometido no rompiera la funcionalidad existente y que las nuevas características funcionaran como se esperaba.

- **Despliegue Automático:** Implementamos un flujo de trabajo para el despliegue automático de la aplicación en un entorno de prueba cada vez que se creaba un **pull request**. Esto permitió a los miembros del equipo revisar y probar los cambios antes de fusionarlos en la rama principal.

- **Notificaciones:** Configuramos notificaciones para alertar al equipo sobre el estado de las acciones, informando si las pruebas habían fallado o si el despliegue se había realizado con éxito. Esto ayudó a mantener a todos informados sobre el progreso y los posibles problemas en tiempo real.

- **Mantenimiento de Versiones:** Utilizamos GitHub Actions para gestionar el versionado de la aplicación, generando automáticamente versiones nuevas basadas en las etiquetas al momento del despliegue.

Estos procesos en **GitHub** facilitaron una colaboración más eficiente y aseguraron que el proyecto avanzara de manera organizada y controlada.


## Conclusiones

En esta primera fase del proyecto **ATuServicio**, hemos logrado construir una base sólida para la implementación del backend utilizando **Spring Boot**. A lo largo del desarrollo, hemos enfrentado diversos retos técnicos y adquirido habilidades importantes que serán clave para las próximas etapas del proyecto.

## Logros del Proyecto

- **Diseño e implementación del modelo de datos:**
  Creación de un modelo entidad-relación con **AppUser**, **Client**, **Freelance**, **Provider**, y **Enterprise**.

- **Creación de Endpoints HTTP:**
  Endpoints para gestionar usuarios, servicios, acuerdos y autenticación con operaciones CRUD.

- **Seguridad del Sistema:**
  Autenticación con **tokens JWT** y validaciones de **RUC** y **DNI** usando las APIs de **SUNAT** y **Reniec**.

- **Pruebas de Integración:**
  Pruebas con **Testcontainers** para validar acceso y restricciones por roles y tokens.

- **Uso de Websockets:**
  Implementación de Websockets para chat en vivo.

- **Consumo de APIs:**
  Integración de APIs externas para funcionalidades adicionales.

- **Manejo de GitHub:**
  Mejora en la gestión de versiones y colaboración en equipo.


## Aprendizajes Clave

- **Uso de Spring Boot:**
  Desarrollo de aplicaciones backend con enfoque en seguridad y autenticación.

- **Colaboración en GitHub:**
  Gestión efectiva de versiones y ramas en equipo.

- **Seguridad:**
  Implementación de sistemas para proteger datos sensibles.

- **Uso de Websockets:**
  Chat en vivo para interacción en tiempo real.

- **Consumo de APIs:**
  Integración de la API de **RENIEC** para validación de documentos y la API de **Maps** para ubicaciones.


## Trabajo Futuro

- **Desarrollo del Frontend:**
  La siguiente fase se centrará en la interfaz de usuario, permitiendo la interacción fluida con las funcionalidades del backend.

- **Implementación de Funcionalidades Avanzadas:**
  Planificamos agregar funciones como recompensas, reseñas y la gestión avanzada de interacciones entre usuarios y proveedores de servicios.


## Apéndices
### Licencia
- #### APACHE LICENSE 2.0

### Referencias
- link del diagrama de entidad relación: [Diagrama entidad relacion](https://drive.google.com/file/d/1Hz45UzKET_EbHwXHYUvbXpdWT7WgiFMR/view?usp=sharing)

