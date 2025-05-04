# Informe Taller 3: Función definirManiobra (Punto 1.2.3)

**Integrantes:**
- Michael Perez Gonzalez 2266121
- julio David Cardona Melendez 2359654
- Daniel Grajales 2067513

**Fecha:** 03 de Mayo de 2024

---

## 1. Función `definirManiobra`

### 1.1. Descripción

`definirManiobra`  Su propósito es encontrar una secuencia 
de movimientos (`Maniobra`) que permita transformar un tren inicial `t1`, ubicado en la vía "principal", en un tren final `t2`, también en la vía "principal".
Se asume que las vías auxiliares "uno" y "dos" están vacías tanto al inicio como al final del proceso. La función  explorara las posibles secuencias de movimientos y devolver una que logre la transformación, 
si existe. Se requiere explícitamente el uso de **expresiones `for`** en la implementación, particularmente en la exploración de movimientos posibles.

### 1.2. Firma

```scala
def definirManiobra(t1: Tren, t2: Tren): Option[Maniobra]
```


# 1.2. Parámetros

- **t1:** Tren: Una List[Vagon] que representa la configuración inicial del tren en la vía "principal".
- **t2:** Tren: Una List[Vagon] que representa la configuración deseada del tren en la vía "principal".

# 1.3. Valor de Retorno

**Option[Maniobra]:**

- Si se encuentra una secuencia de movimientos (List[Movimiento]) que transforma el estado (t1, Nil, Nil) en el estado (t2, Nil, Nil), la función devuelve Some(maniobra), donde maniobra es dicha secuencia.

- Si no se encuentra ninguna secuencia de movimientos que logre la transformación (ya sea porque no existe o porque la búsqueda excede límites prácticos, aunque el algoritmo implementado busca ser completo), la función devuelve None

# 1.4. Detalles de Implementación

### Algoritmo de Búsqueda
La función implementa una Búsqueda en Anchura (BFS - Breadth-First Search) sobre el espacio de estados posibles de la estación de maniobras. BFS garantiza encontrar la solución con el menor número de movimientos, si existe.

### Estructuras de Datos de la Búsqueda
- estadoInicial: (t1, Nil, Nil) - El punto de partida de la búsqueda.

- estadoFinal: (t2, Nil, Nil) - El objetivo de la búsqueda.

- Nodo(estado: Estado, maniobra: Maniobra): Una case class interna para almacenar un estado alcanzado y la secuencia inversa de movimientos (maniobra) utilizada para llegar a él desde el estadoInicial.

- pendientes: Una cola (implementada como List) que contiene los Nodos por explorar. Se añaden nuevos nodos al final y se procesan desde el principio, característico de BFS.

- visitados: Un Set[Estado] que almacena los estados ya explorados para evitar ciclos y trabajo redundante.


### Función Auxiliar Tail-Recursive
La lógica principal de la búsqueda BFS se encapsula en una función auxiliar interna bfs(pendientes, visitados) anotada con @tailrec para asegurar la optimización de recursión de cola y prevenir StackOverflowError en búsquedas potencialmente largas.

### Terminación de la Búsqueda
- **Éxito:** Si se extrae un Nodo de pendientes cuyo estado es igual al estadoFinal, la búsqueda termina exitosamente. Se devuelve Some(maniobra.reverse) (invirtiendo la secuencia acumulada para obtener el orden correcto).


- **Fallo:** Si la lista pendientes se vacía antes de encontrar el estadoFinal, significa que todos los estados alcanzables han sido explorados y el objetivo no se encontró. Se devuelve None.

### Lista de Movimientos Posibles
- Se utiliza una lista posiblesMovimientos (definida en la clase) que contiene instancias de Uno(n) y Dos(n) para un rango razonable de n (excluyendo 0), sobre la cual itera la expresión for.