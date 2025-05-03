# Informe Función aplicarMovimiento (Punto 1.2.1)

**Integrantes:** 
- Michael Perez Gonzalez 2266121
- julio David Cardona Melendez 2359654
- Daniel Grajales 2067513


---

## 1. Función `aplicarMovimiento`

### 1.1. Descripción

La función `aplicarMovimiento` toma un estado actual de la estación de maniobras (`e: Estado`) y un movimiento específico (`m: Movimiento`) y calcula el estado resultante después de aplicar dicho movimiento. Sigue las reglas definidas en el taller:

*   Movimientos con `n = 0` (`Uno(0)`, `Dos(0)`) no tienen efecto.
*   `Uno(n)` con `n > 0`: Mueve los `n` vagones más a la derecha de la vía "principal" al inicio de la vía "uno". Si hay menos de `n` vagones, mueve todos los disponibles.
*   `Uno(n)` con `n < 0`: Mueve los `|n|` vagones más a la izquierda de la vía "uno" al final de la vía "principal". Si hay menos de `|n|` vagones, mueve todos los disponibles.
*   `Dos(n)` con `n > 0`: Mueve los `n` vagones más a la derecha de la vía "principal" al inicio de la vía "dos". Si hay menos de `n` vagones, mueve todos los disponibles.
*   `Dos(n)` con `n < 0`: Mueve los `|n|` vagones más a la izquierda de la vía "dos" al final de la vía "principal". Si hay menos de `|n|` vagones, mueve todos los disponibles.
*   **Restricción Adicional:** La función verifica si el estado resultante mantiene la unicidad de los vagones en toda la estación (usando la función auxiliar `esUnico`). Si el movimiento generara un estado con vagones duplicados, el movimiento no se aplica y se devuelve el estado original.



```scala
def aplicarMovimiento(e: Estado, m: Movimiento): Estado
```

# 1.2. Parámetros

**e: Estado**  
Una tupla `(Tren, Tren, Tren)` que representa el estado actual de las vías (principal, uno, dos).

**m: Movimiento**  
Un objeto `case class` que puede ser `Uno(n)` o `Dos(n)`, indicando el movimiento a realizar.

# 1.3. Valor de Retorno

**Estado**  
La nueva tupla `(Tren, Tren, Tren)` que representa el estado de la estación después de aplicar el movimiento `m`.
- Si `m` es un movimiento nulo (`n=0`) o si el resultado violaría la unicidad de los vagones, se devuelve el estado original `e`.

# 1.4. Detalles de Implementación

### Pattern Matching
Se utiliza *pattern matching* sobre el tipo de Movimiento (`m`) y el valor de `n` para determinar la operación a realizar.

### Manipulación de Listas (Trenes)
- Se usa `splitAt` para dividir las listas (trenes) en las partes que se quedan y las que se mueven.
    - Para `n > 0` (principal -> uno/dos):  
      `e._1.splitAt(e._1.length - n)` separa los `n` últimos elementos.
    - Para `n < 0` (uno/dos -> principal):  
      `e._2.splitAt(math.abs(n))` o `e._3.splitAt(math.abs(n))` separa los `|n|` primeros elementos.
- Se usa `++` para concatenar las listas y formar los nuevos trenes en el estado resultante.

### Validación de Unicidad
- Antes de devolver el nuevo estado calculado, se llama a `esUnico(nuevoEstado)`.
- `esUnico` concatena todos los vagones de las tres vías y verifica si hay duplicados comparando:
    - La longitud de la lista total.
    - La longitud de la lista después de aplicar `distinct`.

# 1.5. Aspectos Funcionales

### Pureza
La función es **pura**. Su salida depende únicamente de sus entradas `e` y `m`.  
No tiene efectos secundarios ni modifica variables externas.

### Inmutabilidad
Trabaja exclusivamente con las estructuras de datos inmutables de Scala:  
`List`, `Tupla`, `case classes`.  
Cada operación (`splitAt`, `++`) crea nuevas listas en lugar de modificar las existentes.

### Pattern Matching
Es una característica idiomática de la programación funcional en Scala, utilizada aquí para manejar diferentes tipos de movimientos de forma declarativa y clara.
