package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class aplicarMovimientosTest extends AnyFunSuite {
  val objAplicarMovs = new Operaciones()

  test("Aplicar movimientos según el ejemplo proporcionado") {
    val e: (List[Char], List[Char], List[Char]) = (List('a', 'b'), List('c'), List('d'))
    val movimientos = List(Uno(1), Dos(1), Uno(-2))
    val resultado = objAplicarMovs.aplicarMovimientos(e, movimientos)

    val esperado = List(
      (List('a', 'b'), List('c'), List('d')),
      (List('a'), List('b', 'c'), List('d')),
      (List(), List('b', 'c'), List('a', 'd')),
      (List('b', 'c'), List(), List('a', 'd'))
    )

    assert(resultado == esperado)
  }
}
