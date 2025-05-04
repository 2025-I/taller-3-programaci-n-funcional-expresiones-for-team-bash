# Informe de Pruebas: AplicarMovimientoTest

**Integrantes:**
- Michael Perez Gonzalez 2266121
- julio David Cardona Melendez 2359654
- Daniel Grajales 2067513

**Fecha:** 03 de Mayo de 2024

---

## 1. Introducción

Este documento describe las pruebas unitarias implementadas en el archivo `AplicarMovimientoTest.scala` para verificar el correcto funcionamiento de la función **`aplicarMovimiento`** de la clase `Operaciones`. A diferencia de `aplicarMovimientosTest` que prueba la aplicación de una secuencia completa y devuelve todos los estados, esta suite se enfoca en verificar el resultado de llamadas individuales a `aplicarMovimiento` o el estado final después de aplicar una secuencia de estas llamadas individuales.

---

## 2. Descripción de las Pruebas

A continuación, se detalla cada prueba definida en la suite:

### 2.1. Test: "Aplicar movimientos según el ejemplo proporcionado"

*   **Objetivo:** Validar el resultado de **cada paso individual** al aplicar la secuencia de movimientos del ejemplo `e1` a `e5` del PDF (Sección 1.2.1).
*   **Configuración:**
    *   Se parte del estado inicial `e1 = (List('a', 'b', 'c', 'd'), Nil, Nil)`.
    *   Se llama secuencialmente a `objAplicarMo.aplicarMovimiento` con los movimientos `Uno(2)`, `Dos(3)`, `Dos(-1)`, `Uno(-2)`, almacenando cada resultado intermedio en `e2`, `e3`, `e4`, `e5`.
*   **Ejecución:** Aplicación manual paso a paso de `aplicarMovimiento`.
*   **Verificación:** Se utiliza `assert` para verificar que **cada uno de los estados** (`e1` hasta `e5`) coincide exactamente con la tupla esperada según el ejemplo del PDF. Esta prueba verifica la correctitud de la transición de estado para cada movimiento específico en esa secuencia.

### 2.2. Test: "Prueba de juguete con Int: listas de tamaño 10 y 10 movimientos variados"

*   **Objetivo:** Verificar el comportamiento de `aplicarMovimiento` al ser aplicado repetidamente sobre un estado inicial pequeño, asegurando que el estado final resultante sea válido.
*   **Configuración:**
    *   Estado inicial `e`: `(List(1, 2, ..., 10), Nil, Nil)`
    *   Movimientos `movimientos`: La misma lista predefinida de 10 movimientos usada en la prueba de juguete de `aplicarMovimientosTest`.
*   **Ejecución:** Se utiliza `movimientos.foldLeft(e)((estado, movimiento) => objAplicarMo.aplicarMovimiento(estado, movimiento))`. Esto aplica **secuencialmente cada movimiento individual** a través de llamadas repetidas a `aplicarMovimiento`, acumulando el estado resultante. Solo se conserva el estado final.
*   **Verificación:** `assert(objAplicarMo.esUnico(resultado))`. Se verifica únicamente que el **estado final**, después de aplicar todos los movimientos uno por uno, mantiene la restricción de unicidad. No se comparan estados intermedios ni la configuración exacta del estado final.

### 2.3. Test: "Prueba pequeña con Int: listas de tamaño 100 y 100 movimientos variados"

*   **Objetivo:** Escalar la prueba anterior a 100 vagones y 100 movimientos generados programáticamente.
*   **Configuración:**
    *   Estado inicial `e`: `(List(1, 2, ..., 100), Nil, Nil)`
    *   Movimientos `movimientos`: 100 movimientos generados con `.map` y lógica de módulo.
*   **Ejecución:** Se utiliza `foldLeft` para aplicar secuencialmente `aplicarMovimiento` con cada movimiento.
*   **Verificación:** `assert(objAplicarMo.esUnico(resultado))` sobre el estado final.

### 2.4. Test: "Prueba mediana con Int: listas de tamaño 500 y 500 movimientos variados"

*   **Objetivo:** Incrementar la escala a 500 vagones y 500 movimientos.
*   **Configuración:**
    *   Estado inicial `e`: `(List(1, 2, ..., 500), Nil, Nil)`
    *   Movimientos `movimientos`: 500 movimientos generados programáticamente.
*   **Ejecución:** Se utiliza `foldLeft` para aplicar secuencialmente `aplicarMovimiento`.
*   **Verificación:** `assert(objAplicarMo.esUnico(resultado))` sobre el estado final.

### 2.5. Test: "Prueba grande con Int: listas de tamaño 1000 y 1000 movimientos variados"

*   **Objetivo:** Realizar un test de estrés con 1000 vagones y 1000 movimientos.
*   **Configuración:**
    *   Estado inicial `e`: `(List(1, 2, ..., 1000), Nil, Nil)`
    *   Movimientos `movimientos`: 1000 movimientos generados programáticamente.
*   **Ejecución:** Se utiliza `foldLeft` para aplicar secuencialmente `aplicarMovimiento`.
*   **Verificación:** `assert(objAplicarMo.esUnico(resultado))` sobre el estado final.

### 2.6. Pruebas con Vagones de Tipo `Char` (2.6.1 a 2.6.4)

Este conjunto de pruebas repite la lógica de las pruebas "juguete", "pequeña", "mediana" y "grande", pero utilizando caracteres (`Char`) como tipo de vagón.

*   **Objetivo General:** Verificar que la función `aplicarMovimiento` funciona correctamente con diferentes tipos de datos para los vagones (`Vagon = Any`).
*   **Configuración:** Estados iniciales `e` con listas de `Char`. Los `movimientos` son los mismos que en las pruebas `Int` correspondientes.
*   **Ejecución:** Se utiliza `foldLeft` para aplicar secuencialmente `aplicarMovimiento` con cada movimiento.
*   **Verificación:** En todas estas pruebas con `Char`, se usa la misma aserción: `assert(objAplicarMo.esUnico(resultado))` sobre el estado final.

---

## 3. Conclusión

La suite `AplicarMovimientoTest` complementa a `aplicarMovimientosTest`. Mientras la primera verifica explícitamente las transiciones individuales de estado en un caso específico y la validez del estado final tras secuencias largas aplicadas mediante `foldLeft`, la segunda (`aplicarMovimientosTest`) verifica la traza completa de estados generada por la función optimizada con recursión de cola. Juntas, proporcionan una buena confianza en la correcta implementación de las operaciones de movimiento de vagones:
*   Verificación detallada paso a paso contra un ejemplo conocido.
*   Pruebas de robustez y mantenimiento de la unicidad tras aplicar secuencias largas de movimientos individuales.
*   Confirmación de funcionamiento con diferentes tipos de datos (`Int`, `Char`).

---