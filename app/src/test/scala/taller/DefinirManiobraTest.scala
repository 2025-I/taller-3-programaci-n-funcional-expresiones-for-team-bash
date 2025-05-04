package taller
import taller.Tipos._
import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DefinirManiobraTest extends AnyFunSuite {
  val objDefinirManiobra = new Operaciones()
    // test de prueba
  test("Definir maniobra para transformar un tren pequeño en otro") {
    val t1: List[Any] = List('a', 'b')
    val t2: List[Any] = List('b', 'a')

    val resultado: Option[Maniobra] = objDefinirManiobra.definirManiobra(t1, t2)

    assert(resultado.nonEmpty)

    val maniobra = resultado.get

    val estadoInicial: Estado = (t1, Nil, Nil)
    val estados = objDefinirManiobra.aplicarMovimientos(estadoInicial, maniobra)

    val estadoFinalEsperado: Estado = (t2, Nil, Nil)
    assert(estados.last == estadoFinalEsperado)
  }
  test("Definir maniobra para listas de tamaño 3") {
  val t1: List[Any] = List('x', 'y', 'z')
  val t2: List[Any] = List('z', 'y', 'x')

  val resultado: Option[Maniobra] = objDefinirManiobra.definirManiobra(t1, t2)

  assert(resultado.nonEmpty)

  val maniobra = resultado.get

  val estadoInicial: Estado = (t1, Nil, Nil)
  val estados = objDefinirManiobra.aplicarMovimientos(estadoInicial, maniobra)

  val estadoFinalEsperado: Estado = (t2, Nil, Nil)
  assert(estados.last == estadoFinalEsperado)
 }
 test("Definir maniobra para listas de tamaño 4 con reordenamiento arbitrario") {
  val t1: List[Any] = List(1, 2, 3, 4)
  val t2: List[Any] = List(4, 3, 2, 1)

  val resultado: Option[Maniobra] = objDefinirManiobra.definirManiobra(t1, t2)

  assert(resultado.nonEmpty)

  val maniobra = resultado.get

  val estadoInicial: Estado = (t1, Nil, Nil)
  val estados = objDefinirManiobra.aplicarMovimientos(estadoInicial, maniobra)

  val estadoFinalEsperado: Estado = (t2, Nil, Nil)
  assert(estados.last == estadoFinalEsperado)
}

//que piden

  
  test("Definir maniobra para listas de tamaño 10") {
    val t1: List[Any] = List.range(1, 11)
    val t2: List[Any] = t1.reverse

    val resultado: Option[Maniobra] = objDefinirManiobra.definirManiobra(t1, t2)

    assert(resultado.nonEmpty)

    val maniobra = resultado.get

    val estadoInicial: Estado = (t1, Nil, Nil)
    val estados = objDefinirManiobra.aplicarMovimientos(estadoInicial, maniobra)

    val estadoFinalEsperado: Estado = (t2, Nil, Nil)
    assert(estados.last == estadoFinalEsperado)
  }
  
  test("Definir maniobra para listas de tamaño 100") {
    val t1: List[Any] = List.range(1, 101)
    val t2: List[Any] = t1.reverse

    val resultado: Option[Maniobra] = objDefinirManiobra.definirManiobra(t1, t2)

    assert(resultado.nonEmpty)

    val maniobra = resultado.get

    val estadoInicial: Estado = (t1, Nil, Nil)
    val estados = objDefinirManiobra.aplicarMovimientos(estadoInicial, maniobra)

    val estadoFinalEsperado: Estado = (t2, Nil, Nil)
    assert(estados.last == estadoFinalEsperado)
  }

  test("Definir maniobra para listas de tamaño 500") {
    val t1: List[Any] = List.range(1, 501)
    val t2: List[Any] = t1.reverse

    val resultado: Option[Maniobra] = objDefinirManiobra.definirManiobra(t1, t2)

    assert(resultado.nonEmpty)

    val maniobra = resultado.get

    val estadoInicial: Estado = (t1, Nil, Nil)
    val estados = objDefinirManiobra.aplicarMovimientos(estadoInicial, maniobra)

    val estadoFinalEsperado: Estado = (t2, Nil, Nil)
    assert(estados.last == estadoFinalEsperado)
  }

  test("Definir maniobra para listas de tamaño 1000") {
    val t1: List[Any] = List.range(1, 1001)
    val t2: List[Any] = t1.reverse

    val resultado: Option[Maniobra] = objDefinirManiobra.definirManiobra(t1, t2)

    assert(resultado.nonEmpty)

    val maniobra = resultado.get

    val estadoInicial: Estado = (t1, Nil, Nil)
    val estados = objDefinirManiobra.aplicarMovimientos(estadoInicial, maniobra)

    val estadoFinalEsperado: Estado = (t2, Nil, Nil)
    assert(estados.last == estadoFinalEsperado)
  }
}
