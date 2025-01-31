# Aplicación de Karaoke

## Descripción General

Este proyecto es una aplicación de Karaoke desarrollada utilizando JavaFX para la interfaz de usuario, Hibernate para ORM (Mapeo Objeto-Relacional) y MariaDB para la base de datos. La aplicación permite a los usuarios iniciar sesión, registrarse y gestionar sus canciones de karaoke. Los usuarios pueden agregar, eliminar y reproducir canciones, y la aplicación lleva un registro del número de veces que se ha reproducido cada canción.

## Características

- **Autenticación de Usuarios**: Los usuarios pueden registrarse e iniciar sesión en la aplicación.
- **Gestión de Canciones**: Los usuarios pueden agregar, eliminar y reproducir canciones.
- **Seguimiento de Reproducciones**: La aplicación lleva un registro del número de veces que se ha reproducido cada canción.
- **Tematización**: La aplicación utiliza el tema Dracula para una interfaz moderna y visualmente atractiva.

## Estructura del Proyecto

- `src/main/java/aed/app`: Contiene las clases principales de la aplicación.
- `src/main/java/aed/controllers`: Contiene los controladores para manejar las interacciones de la UI.
- `src/main/java/aed/dao`: Contiene las clases de Acceso a Datos (DAO) para operaciones con la base de datos.
- `src/main/java/aed/model`: Contiene las clases de entidad que representan las tablas de la base de datos.
- `src/main/java/aed/util`: Contiene clases utilitarias para la inicialización y gestión de la base de datos.
- `src/main/resources/fxml`: Contiene los archivos FXML para el diseño de la UI.
- `src/main/resources/css`: Contiene los archivos CSS para la tematización.
- `src/main/resources`: Contiene el archivo `init.sql` para la inicialización de la base de datos.

## Configuración e Instalación

1. **Clonar el repositorio**:
    ```sh
    git clone https://github.com/Jorgell01/Karaoke.git
    cd Karaoke
    ```

2. **Configurar la base de datos**:
    - Asegúrate de que MariaDB esté instalado y en funcionamiento.
    - Actualiza las credenciales de la base de datos en `hibernate.cfg.xml` si es necesario.

3. **Construir el proyecto**:
    ```sh
    mvn clean install
    ```
   
4. **Compilar el proyecto**:
    ```sh
    mvn compile
    ```

5. **Ejecutar la aplicación**:
    ```sh
    mvn exec:java
    ```

## Inicialización de la Base de Datos

La base de datos se inicializa utilizando el script `init.sql` ubicado en `src/main/resources`. Este script crea las tablas y relaciones necesarias.

## Encriptación

Las contraseñas de los usuarios se almacenan de manera segura utilizando encriptación. La clase `UserDAO` maneja la encriptación y desencriptación de contraseñas utilizando un algoritmo de hashing seguro.

## Tematización

Hacemos uso de la libreria de [mkpaz](https://github.com/mkpaz/atlantafx) para la tematización de la interfaz.

## Clases y Archivos Clave

- **`KaraokeApp.java`**: La clase principal de la aplicación que inicializa la UI y maneja el inicio de sesión de los usuarios.
- **`RootController.java`**: El controlador para la ventana principal de la aplicación, manejando la gestión de canciones.
- **`LoginController.java`**: El controlador para la ventana de inicio de sesión, manejando la autenticación de usuarios.
- **`CancionDAO.java`**: La clase DAO para gestionar los datos de las canciones.
- **`UserDAO.java`**: La clase DAO para gestionar los datos de los usuarios.
- **`DatabaseInitializer.java`**: Una clase utilitaria para inicializar la base de datos.
- **`DatabaseManager`**: Una clase utilitaria para gestionar la conexión a la base de datos.
- **`DialogUtil.java`**: Una clase utilitaria para mostrar diálogos de alerta.
- **`init.sql`**: El script SQL para inicializar la base de datos.

## Uso

1. **Iniciar Sesión/Registrarse**: Los usuarios pueden iniciar sesión o registrarse utilizando la ventana de inicio de sesión.
2. **Agregar Canción**: Los usuarios pueden agregar nuevas canciones proporcionando el título, artista y usuario asociado.
3. **Eliminar Canción**: Los usuarios pueden eliminar canciones seleccionadas de la lista.
4. **Reproducir Canción**: Los usuarios pueden reproducir canciones seleccionadas, lo que incrementa el contador de reproducciones.
5. **Cerrar Sesión**: Los usuarios pueden cerrar sesión en cualquier momento.

## Dependencias

- JavaFX
- Hibernate
- MariaDB
- Maven
- AtlantaFX