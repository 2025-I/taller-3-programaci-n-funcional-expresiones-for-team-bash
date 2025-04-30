package taller

import taller.Tipos._
import scala.annotation.tailrec
class Operaciones {


  def esUnico(estado: Estado): Boolean = {
    val listaTotal = estado._1 ++ estado._2 ++ estado._3
    listaTotal.distinct.length == listaTotal.length
  }

  def aplicarMovimiento(e: Estado, m: Movimiento): Estado = m match {

    case Uno(0) => e

    case Uno(n) if n > 0 =>
      val (rest, mov) = e._1.splitAt(e._1.length - n)
      val nuevoEstado = (rest, mov ++ e._2, e._3)
      if (esUnico(nuevoEstado)) nuevoEstado else throw new IllegalArgumentException("Los vagones no deben contener elementos duplicados.")

    case Uno(n) if n < 0 =>
      val (mov, unorest) = e._2.splitAt(math.abs(n))
      val nuevoEstado = (e._1 ++ mov, unorest, e._3)
      if (esUnico(nuevoEstado)) nuevoEstado else throw new IllegalArgumentException("Los vagones no deben contener elementos duplicados.")

    case Dos(0) => e

    case Dos(n) if n > 0 =>
      val (rest, mov) = e._1.splitAt(e._1.length - n)
      val nuevoEstado = (rest, e._2, mov ++ e._3  )
      if (esUnico(nuevoEstado)) nuevoEstado else throw new IllegalArgumentException("Los vagones no deben contener elementos duplicados.")

    case Dos(n) if n < 0 =>
      val (mov, dosrest) = e._3.splitAt(math.abs(n))
      val nuevoEstado = (e._1 ++ mov, e._2, dosrest)
      if (esUnico(nuevoEstado)) nuevoEstado else throw new IllegalArgumentException("Los vagones no deben contener elementos duplicados.")

    case _ => e
  }

  def aplicarMovimientos(e:Estado , movs:Maniobra): List [Estado] = {
    @tailrec
    def aplicarMovimientosAux(movs:Maniobra , acc:List [Estado]):List [Estado] = movs match {
      case Nil => acc.reverse
      case m::ms =>
        val nuevoEstado = aplicarMovimiento(acc.head, m)
        aplicarMovimientosAux(ms, nuevoEstado :: acc)
      }
      aplicarMovimientosAux(movs, List (e))
  }  

  

  
}
