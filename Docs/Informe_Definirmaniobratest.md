# Informe de Pruebas: DefinirManiobraTest

**Integrantes:**
- Michael Perez Gonzalez 2266121
- julio David Cardona Melendez 2359654
- Daniel Grajales 2067513

**Fecha:** 03 de Mayo de 2024

---

## 1. Introducción

Este documento describe las pruebas unitarias implementadas en el archivo `DefinirManiobraTest` para verificar el correcto funcionamiento de la función **`definirManiobra`** de la clase `Operaciones`. El objetivo principal de `definirManiobra` es encontrar una secuencia de movimientos (`Maniobra`) que transforme un tren inicial `t1` en un tren final `t2`, ambos situados en la vía principal, manteniendo las vías auxiliares vacías al inicio y al final.

Las pruebas aquí definidas verifican dos aspectos clave:
1.  Que la función sea capaz de encontrar *alguna* secuencia de maniobras para los casos propuestos (verificando que el resultado `Option[Maniobra]` no sea `None`).
2.  Que la maniobra encontrada, al ser aplicada al estado inicial, efectivamente produzca el estado final deseado (verificando el último estado devuelto por `aplicarMovimientos`).

---

## 2. Descripción de las Pruebas

A continuación, se detalla cada prueba definida en la suite:

### 2.1. Test: "Definir maniobra para transformar un tren pequeño en otro"

*   **Objetivo:** Verificar la funcionalidad básica con la entrada más simple posible: invertir un tren de dos vagones.
*   **Configuración:**
    *   Tren inicial `t1`: `List('a', 'b')`
    *   Tren final `t2`: `List('b', 'a')`
*   **Ejecución:**
    1.  Se llama a `objDefinirManiobra.definirManiobra(t1, t2)`.
    2.  Se verifica que el resultado `resultado` no esté vacío (`nonEmpty`).
    3.  Se extrae la `maniobra` encontrada.
    4.  Se aplica la `maniobra` al estado inicial `(t1, Nil, Nil)` usando `aplicarMovimientos`.
*   **Verificación:** Se compara el último estado de la secuencia resultante con el estado final esperado `(t2, Nil, Nil)`.

### 2.2. Test: "Definir maniobra para listas de tamaño 3"

*   **Objetivo:** Probar con un tamaño ligeramente mayor (3 vagones) y un reordenamiento específico (inversión).
*   **Configuración:**
    *   Tren inicial `t1`: `List('x', 'y', 'z')`
    *   Tren final `t2`: `List('z', 'y', 'x')`
*   **Ejecución:** Mismo procedimiento que la prueba anterior (llamar a `definirManiobra`, verificar `nonEmpty`, aplicar la maniobra con `aplicarMovimientos`).
*   **Verificación:** Comparar el último estado resultante con `(t2, Nil, Nil)`.

### 2.3. Test: "Definir maniobra para listas de tamaño 4 con reordenamiento arbitrario"

*   **Objetivo:** Probar con 4 vagones y un reordenamiento completo (inversión), usando enteros.
*   **Configuración:**
    *   Tren inicial `t1`: `List(1, 2, 3, 4)`
    *   Tren final `t2`: `List(4, 3, 2, 1)`
*   **Ejecución:** Mismo procedimiento.
*   **Verificación:** Comparar el último estado resultante con `(t2, Nil, Nil)`.

### 2.4. Tests con Tamaños Crecientes (10, 100, 500, 1000)

Este conjunto de pruebas (`"Definir maniobra para listas de tamaño X"`) tiene un objetivo común pero aplicado a escalas crecientes.

*   **Objetivo General:** Verificar que `definirManiobra` puede encontrar una solución para trenes progresivamente más largos, donde la tarea es invertir completamente el orden de los vagones. Estas pruebas también evalúan implícitamente la eficiencia y correctitud del algoritmo de búsqueda (BFS) subyacente en `definirManiobra` y de las operaciones de `aplicarMovimiento` usadas por la búsqueda.
*   **Configuración:**
    *   Para cada tamaño `X` (10, 100, 500, 1000):
        *   Tren inicial `t1`: `List.range(1, X + 1)`
        *   Tren final `t2`: `t1.reverse`
*   **Ejecución:** Para cada tamaño, se sigue el mismo procedimiento:
    1.  Llamar a `objDefinirManiobra.definirManiobra(t1, t2)`.
    2.  Verificar `resultado.nonEmpty`.
    3.  Extraer la `maniobra`.
    4.  Aplicar la `maniobra` al estado inicial `(t1, Nil, Nil)` usando `aplicarMovimientos`.
*   **Verificación:** Para cada tamaño, comparar el último estado de la secuencia resultante con el estado final esperado `(t2, Nil, Nil)`.

**Nota Importante:** La complejidad de la búsqueda de maniobras puede crecer rápidamente con el tamaño del tren. El éxito de las pruebas más grandes (100, 500, 1000) depende significativamente de la eficiencia de la implementación de `definirManiobra` (en particular, el BFS y la gestión del conjunto `visitados`) y de las operaciones de `aplicarMovimiento`.

---

## 3. Conclusión

La suite de pruebas `DefinirManiobraTest` evalúa la capacidad de la función `definirManiobra` para resolver el problema central del taller: encontrar una secuencia válida de movimientos para reordenar un tren.
*   Se cubren casos base con trenes pequeños.
*   Se incluyen pruebas con tamaños crecientes para verificar la escalabilidad y robustez de la solución.
*   La verificación en dos pasos (primero, encontrar una maniobra; segundo, aplicarla y comprobar el resultado) asegura tanto que la función retorna una solución cuando debe, como que la solución encontrada es correcta.

Estas pruebas son cruciales para validar la lógica de búsqueda y la integración de las diferentes funciones (`definirManiobra`, `aplicarMovimientos`, `aplicarMovimiento`) en la solución final del problema de maniobras.

---