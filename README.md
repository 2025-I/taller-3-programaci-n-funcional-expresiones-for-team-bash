[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/D84ercCa)
# Documentación del Taller 3

## Introducción

El taller se centra en resolver un problema de logística (maniobras de trenes en una estación) utilizando principios de programación funcional en Scala. Un objetivo clave es adquirir destreza en el uso de **expresiones `for`** para generar, filtrar y transformar secuencias de manera declarativa, además de aplicar conceptos como inmutabilidad, recursión (incluyendo recursión de cola) y manejo de estado funcional.

## Integrantes del Grupo

- Michael Perez Gonzalez 2266121
- julio David Cardona Melendez 2359654
- Daniel Grajales 2067513

## Objetivos del Taller

1.  Modelar un sistema (estación de maniobras) utilizando tipos de datos funcionales (tuplas, listas, case classes).
2.  Implementar funciones puras para transformar el estado del sistema basándose en movimientos definidos.
3.  Utilizar recursión de cola (`@tailrec`) para procesar secuencias de operaciones eficientemente.
4.  Aplicar **expresiones `for`** de manera efectiva para explorar posibilidades o generar secuencias complejas (especialmente en la búsqueda de soluciones).
5.  Desarrollar una solución funcional completa que encuentre una secuencia de maniobras para alcanzar un estado deseado.
6.  Adherirse estrictamente a los principios de la programación funcional: inmutabilidad, ausencia de efectos secundarios, no usar bucles `while` o `for` imperativos ni variables `var`.
7.  Documentar adecuadamente el código, el diseño y el proceso de desarrollo.

## Contenido del Taller

El taller se estructura en torno a la implementación de las siguientes funcionalidades clave:

| Punto | Descripción                                                                 | Función Principal     |
| :---- | :-------------------------------------------------------------------------- | :-------------------- |
| 1.1   | Definición del modelo: Vagones, Trenes, Estado y Movimientos.               | Tipos y Case Classes  |
| 1.2.1 | Aplicar un único movimiento a un estado de la estación.                     | `aplicarMovimiento`   |
| 1.2.2 | Aplicar una secuencia de movimientos y obtener la traza de estados (con `@tailrec`). | `aplicarMovimientos` |
| 1.2.3 | Encontrar una secuencia de maniobras para transformar un tren inicial en uno final (usando `for` expressions y búsqueda). | `definirManiobra`     |

La documentación detallada de cada función implementada se encuentra en archivos Markdown individuales dentro de la carpeta `docs/` del repositorio.

## Conclusión

Este taller proporcionó una experiencia práctica en la aplicación de técnicas de programación funcional para resolver un problema de planificación y búsqueda. Se reforzó el uso de tipos de datos inmutables, la recursión de cola y, fundamentalmente, el poder expresivo de las **expresiones `for`** en Scala para manejar colecciones y cómputos complejos de manera concisa y declarativa. La resolución del problema de las maniobras de trenes demuestra cómo estos conceptos se combinan para crear soluciones robustas y mantenibles.

---

**Autores:**
- Michael Perez Gonzalez 2266121
- julio David Cardona Melendez 2359654
- Daniel Grajales 2067513