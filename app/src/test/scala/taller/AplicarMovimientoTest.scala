package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AplicarMovimientoTest extends AnyFunSuite {
  val objAplicarMo = new Operaciones()

  test("Aplicar movimientos según el ejemplo proporcionado") {
    val e1: (List[Char], List[Char], List[Char]) = (List('a', 'b', 'c', 'd'), Nil, Nil)
    val e2 = objAplicarMo.aplicarMovimiento(e1, Uno(2))
    val e3 = objAplicarMo.aplicarMovimiento(e2, Dos(3))
    val e4 = objAplicarMo.aplicarMovimiento(e3, Dos(-1))
    val e5 = objAplicarMo.aplicarMovimiento(e4, Uno(-2))

    assert(e1 == (List('a', 'b', 'c', 'd'), List(), List()))
    assert(e2 == (List('a', 'b'), List('c', 'd'), List()))
    assert(e3 == (List(), List('c', 'd'), List('a', 'b')))
    assert(e4 == (List('a'), List('c', 'd'), List('b')))
    assert(e5 == (List('a', 'c', 'd'), List(), List('b')))
  }

}

