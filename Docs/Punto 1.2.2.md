# Informe Función aplicarMovimientos (Punto 1.2.2)

**Integrantes:** 
- Michael Perez Gonzalez 2266121
- julio David Cardona Melendez 2359654
- Daniel Grajales 2067513

---

## 1. Función `aplicarMovimientos`

### 1.1. Descripción

La función `aplicarMovimientos` toma un estado inicial `e` y una secuencia de movimientos `movs` (una `Maniobra`) y devuelve una lista que contiene la secuencia completa de estados por los que pasa la estación, comenzando por el estado inicial `e` y terminando con el estado resultante después de aplicar el último movimiento de la lista `movs`.


```scala
def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado]
```

### 1.2. Parámetros
#### e: 
Estado: El estado inicial de la estación de maniobras.
#### movs: 
Maniobra: Una List[Movimiento] que representa la secuencia de movimientos a aplicar en orden.


### 1.3. Valor de Retorno
List[Estado]: Una lista de estados. El primer elemento es el estado inicial e. 
Cada elemento subsiguiente es el resultado de aplicar el movimiento correspondiente de movs al estado anterior en 
la lista (utilizando la función aplicarMovimiento). 
Si movs tiene k movimientos, la lista resultante tendrá k + 1 estados.


### 1.4. Detalles de Implementación
#### Función Auxiliar Tail-Recursive: 
Se utiliza una función auxiliar interna aplicarMovimientosAux anotada con @tailrec para garantizar 
la optimización de la recursión de cola y evitar StackOverflowError para listas largas de movimientos.

#### Acumulador: 
La función auxiliar aplicarMovimientosAux utiliza un parámetro acc: List[Estado] como acumulador. 
Este acumulador guarda la secuencia de estados generados hasta el momento, pero en orden inverso (el estado más reciente se añade a la cabeza de acc).


#### Proceso Recursivo:
##### Caso Base: 
Si la lista de movimientos movs está vacía (Nil), significa que se han procesado todos los movimientos. 
Se invierte el acumulador (acc.reverse) para obtener el orden cronológico correcto de los estados y se devuelve.
##### Paso Recursivo:
Si movs no está vacía (m :: ms), se toma el estado más reciente (que está en la cabeza del acumulador, acc.head) 
y se le aplica el movimiento actual m usando la función aplicarMovimiento. El nuevoEstado resultante se añade a la cabeza del acumulador (nuevoEstado :: acc). 
Luego, se llama recursivamente a aplicarMovimientosAux con el resto de los movimientos (ms) y el acumulador actualizado.
##### Llamada Inicial: 
La función principal aplicarMovimientos inicia el proceso llamando a aplicarMovimientosAux con la lista completa de movimientos movs y un acumulador inicial que contiene solo el estado inicial (List(e)).

#### 1.5. Aspectos Funcionales
##### Pureza e Inmutabilidad: 
Al igual que su dependencia aplicarMovimiento, esta función es pura y trabaja con datos inmutables.
Recursión de Cola (@tailrec): La implementación utiliza recursión de cola, que es una técnica funcional esencial para procesar secuencias (como listas de movimientos) de manera eficiente en términos de memoria, ya que el compilador puede optimizarla para usar un espacio constante en la pila.
##### Composición:
La función compone la funcionalidad de aplicarMovimiento repetidamente para construir la secuencia de estados.
Manejo de Listas: Utiliza operaciones funcionales estándar sobre listas como :: (cons), head, Nil (patrón de lista vacía) y reverse.