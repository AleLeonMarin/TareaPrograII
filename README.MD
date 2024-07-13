## UNA-KIDS

## Introducción

Este proyecto fue realizado en el curso de Programación II en la Universidad de Costa Rica. El proyecto consiste en una cooperativa orientada a niños para que inicien en su mundo financiero.

## En qué consiste

El proyecto consta de tres partes: una parte administrativa llamada "profesor", una parte de funcionarios y una parte de asociados.

1. **Profesor:** Configura todo el sistema de la cooperativa, incluyendo el nombre, el logo y los tipos de cuentas. Puede editar, eliminar y crear configuraciones.

2. **Funcionarios:** Realizan las tareas de una persona cajera dentro de una cooperativa. Pueden agregar cuentas a los asociados mediante un sistema de arrastrar y soltar (drag and drop), crear carnets, eliminar cuentas, realizar depósitos, ver depósitos pendientes, y registrar, editar y eliminar asociados.

3. **Asociado:** Permite registrarse como asociado, realizar depósitos, ver estados de cuenta y jugar una adivinanza.

## Características

### Profesor
- Edición del nombre y logo de la cooperativa.
- Creación, eliminación y edición de tipos de cuentas.

### Funcionarios
- Registrar, editar, eliminar y buscar asociados.
- Creación de carnets.
- Apertura y cierre de cuentas.
- Realización de depósitos a cuentas.
- Verificación del buzón.

### Asociado
- Registro de asociado.
- Visualización de estados de cuenta resumido o detallado.
- Realización de depósitos en el buzón.
- Juego de adivinanza para ganar dinero.

## Cómo usar

1. Contar con Java 21.
2. Contar con Apache Maven.
3. Ejecutar el siguiente comando en la terminal:

    ```bash
    mvn clean install
    ```

4. Ingresar a la carpeta `target/` y abrir la terminal en dicha carpeta.
5. Si quieres ingresar como Administrativo, ejecutar el siguiente comando en tu terminal:

    ```bash
    java -jar Tarea-1.0-shaded.jar P
    ```

6. Si quieres ingresar como Funcionario, ejecutar el siguiente comando en tu terminal:

    ```bash
    java -jar Tarea-1.0-shaded.jar F
    ```

7. Si quieres ingresar como Asociado, ejecutar el siguiente comando en tu terminal:

    ```bash
    java -jar Tarea-1.0-shaded.jar A
    ```

8. Disfrutar del sistema.

## Desarrolladores

- Alejandro León Marín
- Eslyn Jara Calderón

## Contacto

Correo electrónico: aleleonmarin01@gmail.com
