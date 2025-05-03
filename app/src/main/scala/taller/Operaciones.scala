package taller

import taller.Tipos._
import scala.annotation.tailrec

class Operaciones {

  def esUnico(estado: Estado): Boolean = {
    val listaTotal = estado._1 ++ estado._2 ++ estado._3
    listaTotal.distinct.length == listaTotal.length
  }

  def estadoValido(estado: Estado): Boolean = esUnico(estado)

  val posiblesMovimientos: List[Movimiento] = ((for {
    n <- -10 to 10 if n != 0
  } yield Uno(n)) ++ (for {
    n <- -10 to 10 if n != 0
  } yield Dos(n))).toList

  def aplicarMovimiento(e: Estado, m: Movimiento): Estado = m match {
    case Uno(0) => e

    case Uno(n) if n > 0 =>
      val (rest, mov) = e._1.splitAt(e._1.length - n)
      val nuevoEstado = (rest, mov ++ e._2, e._3)
      if (esUnico(nuevoEstado)) nuevoEstado else e

    case Uno(n) if n < 0 =>
      val (mov, unorest) = e._2.splitAt(math.abs(n))
      val nuevoEstado = (e._1 ++ mov, unorest, e._3)
      if (esUnico(nuevoEstado)) nuevoEstado else e

    case Dos(0) => e

    case Dos(n) if n > 0 =>
      val (rest, mov) = e._1.splitAt(e._1.length - n)
      val nuevoEstado = (rest, e._2, mov ++ e._3)
      if (esUnico(nuevoEstado)) nuevoEstado else e

    case Dos(n) if n < 0 =>
      val (mov, dosrest) = e._3.splitAt(math.abs(n))
      val nuevoEstado = (e._1 ++ mov, e._2, dosrest)
      if (esUnico(nuevoEstado)) nuevoEstado else e

    case _ => e
  }

  def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado] = {
    @tailrec
    def aplicarMovimientosAux(movs: Maniobra, acc: List[Estado]): List[Estado] = movs match {
      case Nil => acc.reverse
      case m :: ms =>
        val nuevoEstado = aplicarMovimiento(acc.head, m)
        aplicarMovimientosAux(ms, nuevoEstado :: acc)
    }
    aplicarMovimientosAux(movs, List(e))
  }

  def definirManiobra(t1: Tren, t2: Tren): Option[Maniobra] = {
    val estadoInicial: Estado = (t1, Nil, Nil)
    val estadoFinal: Estado = (t2, Nil, Nil)

    case class Nodo(estado: Estado, maniobra: Maniobra)

    @tailrec
    def bfs(pendientes: List[Nodo], visitados: Set[Estado]): Option[Maniobra] = pendientes match {
      case Nil => None 
      case Nodo(estado, maniobra) :: resto =>
        if (estado == estadoFinal) Some(maniobra.reverse)
        else if (visitados.contains(estado)) bfs(resto, visitados)
        else {
          val nuevosNodos = for {
            mov <- posiblesMovimientos
            nuevoEstado <- aplicarMovimientoFunc(estado, mov)
            if !visitados.contains(nuevoEstado)
          } yield Nodo(nuevoEstado, mov :: maniobra)

          bfs(resto ++ nuevosNodos, visitados + estado)
        }
    }

    def aplicarMovimientoFunc(e: Estado, m: Movimiento): Option[Estado] =
      Some(aplicarMovimiento(e, m))

    bfs(List(Nodo(estadoInicial, Nil)), Set.empty)
  }
}
