# Informe de Corrección - Taller 3

**Integrantes:**

- Michael Perez Gonzalez 2266121
- julio David Cardona Melendez 2359654
- Daniel Grajales 2067513

**Fecha:**  20 de Mayo de 2024

---

## 1. Introducción

En este informe, explicamos detalladamente las correcciones aplicadas a las funciones del Taller 3 de Fundamentos de Programación Funcional y Concurrente. Este taller aborda el problema de maniobras de trenes, y las funciones revisadas incluyen `aplicarMovimiento`, `aplicarMovimientos` y `definirManiobra`. El objetivo principal es demostrar que estas funciones cumplen con sus especificaciones, utilizando técnicas de análisis de patrones y verificación de invariantes para garantizar su correcto funcionamiento.

---

## 2. Argumentación sobre `aplicarMovimiento`

### 2.1. Especificación

La función `aplicarMovimiento` recibe un estado `e = (principal, uno, dos)` y un movimiento `m`, retornando el estado resultante `e'`. Las reglas establecen que:

* El movimiento debe ser válido.
* El estado resultante no debe contener vagones duplicados.
* Si el movimiento es inválido, la función debe devolver el estado original `e`.

```scala
def aplicarMovimiento(e: Estado, m: Movimiento): Estado
```

### 2.2. Análisis por Casos

#### Caso 1: `Uno(0)` o `Dos(0)`

La función devuelve el estado original `e`. Esto es correcto, ya que mover 0 vagones no altera el estado.

#### Caso 2: `Uno(n)` con `n > 0`

* Se calcula `(rest, mov) = e._1.splitAt(e._1.length - n)`.

    * `mov` contiene los últimos `n` vagones de la vía principal.
    * `rest` contiene los restantes.
* El nuevo estado se construye como `(rest, mov ++ e._2, e._3)`.
* Si el nuevo estado es válido (unicidad de vagones), se devuelve; de lo contrario, se retorna `e`.

#### Caso 3: `Uno(n)` con `n < 0`

* Se calcula `(mov, unorest) = e._2.splitAt(math.abs(n))`.
* El nuevo estado se construye como `(e._1 ++ mov, unorest, e._3)`.
* La validez se verifica de forma similar al caso anterior.

#### Caso 4: `Dos(n)`

Estos casos son análogos a los de `Uno(n)`, pero involucrando la vía `dos` (`e._3`) en lugar de la vía `uno` (`e._2`).

#### Caso por Defecto

Devuelve el estado original `e`. Este comportamiento asegura robustez frente a movimientos inesperados.

**Conclusión:**
La función implementa correctamente las reglas de transformación de estado y verifica las restricciones requeridas.

---

## 3. Argumentación sobre `aplicarMovimientos`

### 3.1. Especificación

La función `aplicarMovimientos` aplica una lista de movimientos a un estado inicial `e` y retorna una lista de estados resultantes:

```scala
def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado]
```

### 3.2. Invariante de Ciclo

La función auxiliar `aplicarMovimientosAux` mantiene el siguiente invariante:

* `movsPendientes` contiene los movimientos por aplicar.
* `acc` es la lista de estados generados hasta ahora, en orden inverso.
* En cada iteración, se procesa un movimiento, generando un nuevo estado que se agrega a `acc`.

**Demostración:**

1. **Inicialización:** Con `movsPendientes = movs` y `acc = List(e)`, se cumple el invariante, ya que no hay movimientos procesados.
2. **Mantenimiento:** Cada iteración aplica un movimiento al estado más reciente, actualizando `acc` correctamente.
3. **Terminación:** Cuando `movsPendientes` está vacío, `acc.reverse` contiene los estados generados en orden cronológico.

**Conclusión:**
La función es eficiente en memoria (recursión de cola) y garantiza resultados correctos para cualquier lista de movimientos.

---

## 4. Argumentación sobre `definirManiobra`

### 4.1. Especificación

La función `definirManiobra` encuentra una secuencia de movimientos que transforma un tren inicial `t1` en un tren final `t2`, o retorna `None` si no es posible:

```scala
def definirManiobra(t1: Tren, t2: Tren): Option[Maniobra]
```

### 4.2. Uso de BFS

La implementación utiliza Búsqueda en Anchura (BFS):

* **Espacio de Estados:** Cada estado es una combinación de trenes en las tres vías.
* **Exploración:** Para cada estado, se generan nuevos estados aplicando todos los movimientos posibles.
* **Detección de Solución:** Si un estado generado coincide con el estado final `(t2, Nil, Nil)`, se retorna la secuencia de movimientos que lo produjo.

**Propiedades de BFS:**

* **Compleción:** Encuentra una solución si existe.
* **Optimalidad:** Retorna una secuencia de movimientos de longitud mínima.

**Conclusión:**
La función garantiza la solución correcta y eficiente para el problema planteado.

---

## 5. Conclusiones

Las correcciones aplicadas aseguran que las funciones cumplen con sus especificaciones mediante un enfoque riguroso de análisis por casos, verificación de invariantes y propiedades de algoritmos de búsqueda. Esto respalda su uso confiable en la resolución del problema de maniobras de trenes.
