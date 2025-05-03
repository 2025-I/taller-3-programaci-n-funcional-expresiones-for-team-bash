# Informe aplicarMovimientosTest

**Integrantes:**
- Michael Perez Gonzalez 2266121
- julio David Cardona Melendez 2359654
- Daniel Grajales 2067513

**Fecha:** 20 de Mayo de 2024

---

## 1. Introducción

Este documento describe las pruebas unitarias implementadas en el archivo `aplicarMovimientosTest.scala` para verificar el correcto funcionamiento de la función `aplicarMovimientos` de la clase `Operaciones`. Estas pruebas cubren diversos escenarios, incluyendo casos específicos, diferentes tamaños de entrada y distintos tipos de datos para los vagones.

---

## 2. Descripción de las Pruebas

A continuación, se detalla cada prueba definida en la suite:

### 2.1. Test: "Aplicar movimientos según el ejemplo"

*   **Objetivo:** Validar que la función `aplicarMovimientos` reproduce exactamente la secuencia de estados mostrada como ejemplo en la documentación del taller (Figura 3).
*   **Configuración:**
    *   Estado inicial `e`: `(List('a', 'b'), List('c'), List('d'))`
    *   Movimientos `movimientos`: `List(Uno(1), Dos(1), Uno(-2))`
    *   Se define una lista `esperado` que contiene la secuencia exacta de 4 estados que se deberían obtener.
*   **Ejecución:** Se llama a `objAplicarMovs.aplicarMovimientos(e, movimientos)`.
*   **Verificación:** Se utiliza `assert(resultado == esperado)` para comparar la lista de estados devuelta por la función con la lista `esperado`. Esta es una prueba de exactitud contra un caso conocido.

### 2.2. Test: "Prueba de juguete: listas de tamaño 10 y 10 movimientos variados"

*   **Objetivo:** Realizar una prueba básica (sanity check) con un número pequeño de vagones (enteros 1 a 10) y una secuencia corta (10) de movimientos variados definidos manualmente.
*   **Configuración:**
    *   Estado inicial `e`: `(List(1, 2, ..., 10), Nil, Nil)`
    *   Movimientos `movimientos`: Una lista predefinida de 10 movimientos `Uno` y `Dos`, con valores `n` positivos y negativos.
*   **Ejecución:** Se llama a `objAplicarMovs.aplicarMovimientos(e, movimientos)`.
*   **Verificación:**
    *   `assert(resultado.nonEmpty)`: Asegura que la función devuelve una lista de estados (al menos el inicial).
    *   `assert(objAplicarMovs.esUnico(resultado.last))`: Verifica que el **estado final**, después de todos los movimientos, sigue conteniendo vagones únicos. Esto comprueba indirectamente que la restricción de unicidad se mantuvo durante la ejecución.

### 2.3. Test: "Prueba pequeña: listas de tamaño 100 y 100 movimientos variados"

*   **Objetivo:** Aumentar la escala a 100 vagones y 100 movimientos para probar la robustez y eficiencia (especialmente la recursión de cola) con entradas más grandes.
*   **Configuración:**
    *   Estado inicial `e`: `(List(1, 2, ..., 100), Nil, Nil)`
    *   Movimientos `movimientos`: Se generan 100 movimientos **programáticamente** usando `.map` y operaciones módulo (`%`) para crear una secuencia variada de `Uno` y `Dos` con valores `n` pequeños.
*   **Ejecución:** Se llama a `objAplicarMovs.aplicarMovimientos(e, movimientos)`.
*   **Verificación:** Igual que la prueba de juguete: `assert(resultado.nonEmpty)` y `assert(objAplicarMovs.esUnico(resultado.last))`.

### 2.4. Test: "Prueba mediana: listas de tamaño 500 y 500 movimientos variados"

*   **Objetivo:** Incrementar aún más la escala (500 vagones, 500 movimientos) para un test de estrés moderado, relevante para verificar que la recursión de cola evita `StackOverflowError`.
*   **Configuración:**
    *   Estado inicial `e`: `(List(1, 2, ..., 500), Nil, Nil)`
    *   Movimientos `movimientos`: Se generan 500 movimientos programáticamente con una lógica de módulo ligeramente diferente a la prueba anterior.
*   **Ejecución:** Se llama a `objAplicarMovs.aplicarMovimientos(e, movimientos)`.
*   **Verificación:** `assert(resultado.nonEmpty)` y `assert(objAplicarMovs.esUnico(resultado.last))`.

### 2.5. Test: "Prueba grande: listas de tamaño 1000 y 1000 movimientos variados"

*   **Objetivo:** Realizar un test de estrés significativo (1000 vagones, 1000 movimientos) para evaluar el comportamiento con entradas grandes.
*   **Configuración:**
    *   Estado inicial `e`: `(List(1, 2, ..., 1000), Nil, Nil)`
    *   Movimientos `movimientos`: Se generan 1000 movimientos programáticamente con otra variación en la lógica de módulo.
*   **Ejecución:** Se llama a `objAplicarMovs.aplicarMovimientos(e, movimientos)`.
*   **Verificación:** `assert(resultado.nonEmpty)` y `assert(objAplicarMovs.esUnico(resultado.last))`.

### 2.6. Pruebas con Vagones de Tipo `Char` (2.6.1 a 2.6.4)

Este conjunto de pruebas repite la lógica de las pruebas "juguete", "pequeña", "mediana" y "grande", pero utilizando caracteres (`Char`) como tipo de vagón en lugar de enteros (`Int`).

*   **Objetivo General:** Verificar que las funciones `aplicarMovimiento` y `aplicarMovimientos` funcionan correctamente con diferentes tipos de datos para los vagones, aprovechando que `type Vagon = Any`.
*   **Configuración:**
    *   Los estados iniciales `e` se crean con listas de caracteres (ej: `'a' to 'j'`, `'a' to 'z'`, o listas más largas generadas repitiendo caracteres).
    *   Las secuencias de `movimientos` (tanto las predefinidas como las generadas programáticamente) son **idénticas** a las usadas en las pruebas correspondientes con enteros.
*   **Ejecución:** Se llama a `objAplicarMovs.aplicarMovimientos(e, movimientos)` para cada caso.
*   **Verificación:** En todas estas pruebas con `Char`, se usan las mismas aserciones: `assert(resultado.nonEmpty)` y `assert(objAplicarMovs.esUnico(resultado.last))`.

---

## 3. Conclusión

La suite de pruebas `aplicarMovimientosTest` proporciona una cobertura razonable para la función `aplicarMovimientos`. Incluye:
*   Una verificación contra un resultado exacto conocido.
*   Pruebas con diferentes tamaños de entrada (desde pequeños hasta grandes) para evaluar robustez y eficiencia.
*   Pruebas con diferentes tipos de datos (`Int` y `Char`) para confirmar la genericidad del tipo `Vagon`.
*   Verificación implícita de la restricción de unicidad a través del chequeo del estado final.

Estas pruebas aumentan la confianza en que la función `aplicarMovimientos` se comporta como se espera según la especificación del taller.

---