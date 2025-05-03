package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class aplicarMovimientosTest extends AnyFunSuite {
  val objAplicarMovs = new Operaciones()

  test("Aplicar movimientos según el ejemplo proporcionado") {
    val e: (List[Any], List[Any], List[Any]) = (List('a', 'b'), List('c'), List('d'))
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
  test("Prueba de juguete: listas de tamaño 10 y 10 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (List.range(1, 11), Nil, Nil)
    val movimientos = List(Uno(1), Dos(-1), Uno(2), Dos(1), Uno(-3), Dos(-2), Uno(1), Dos(2), Uno(-1), Dos(3))
    val resultado = objAplicarMovs.aplicarMovimientos(e, movimientos)
    assert(resultado.nonEmpty)
    assert(objAplicarMovs.esUnico(resultado.last))
  }
  test("Prueba pequeña: listas de tamaño 100 y 100 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (List.range(1, 101), Nil, Nil)
    val movimientos = (1 to 100).map(n => if (n % 2 == 0) Uno(n % 5) else Dos(-(n % 3))).toList
    val resultado = objAplicarMovs.aplicarMovimientos(e, movimientos)
    assert(resultado.nonEmpty)
    assert(objAplicarMovs.esUnico(resultado.last))
  }
  test("Prueba mediana: listas de tamaño 500 y 500 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (List.range(1, 501), Nil, Nil)
    val movimientos = (1 to 500).map(n => if (n % 3 == 0) Uno(n % 7) else Dos(-(n % 4))).toList
    val resultado = objAplicarMovs.aplicarMovimientos(e, movimientos)
    assert(resultado.nonEmpty)
    assert(objAplicarMovs.esUnico(resultado.last))
  }
  test("Prueba grande: listas de tamaño 1000 y 1000 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (List.range(1, 1001), Nil, Nil)
    val movimientos = (1 to 1000).map(n => if (n % 2 == 0) Uno(-(n % 6)) else Dos(n % 5)).toList
    val resultado = objAplicarMovs.aplicarMovimientos(e, movimientos)
    assert(resultado.nonEmpty)
    assert(objAplicarMovs.esUnico(resultado.last))
  }
  //Movimientos con Char
  //Pruebas con Char
  test("Prueba de juguete con Char: listas de tamaño 10 y 10 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (('a' to 'j').toList, Nil, Nil)
    val movimientos = List(Uno(1), Dos(-1), Uno(2), Dos(1), Uno(-3), Dos(-2), Uno(1), Dos(2), Uno(-1), Dos(3))
    val resultado = objAplicarMovs.aplicarMovimientos(e, movimientos)
    assert(resultado.nonEmpty)
    assert(objAplicarMovs.esUnico(resultado.last))
  }
  test("Prueba pequeña con Char: listas de tamaño 100 y 100 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (('a' to 'z').toList, Nil, Nil)
    val movimientos = (1 to 100).map(n => if (n % 2 == 0) Uno(n % 5) else Dos(-(n % 3))).toList
    val resultado = objAplicarMovs.aplicarMovimientos(e, movimientos)
    assert(resultado.nonEmpty)
    assert(objAplicarMovs.esUnico(resultado.last))
  }
  test("Prueba mediana con Char: listas de tamaño 500 y 500 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (('a' to 'z').flatMap(c => List(c)).take(500).toList, Nil, Nil)
    val movimientos = (1 to 500).map(n => if (n % 3 == 0) Uno(n % 7) else Dos(-(n % 4))).toList
    val resultado = objAplicarMovs.aplicarMovimientos(e, movimientos)
    assert(resultado.nonEmpty)
    assert(objAplicarMovs.esUnico(resultado.last))
  }
  test("Prueba grande con Char: listas de tamaño 1000 y 1000 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (('a' to 'z').flatMap(c => List(c)).take(1000).toList, Nil, Nil)
    val movimientos = (1 to 1000).map(n => if (n % 2 == 0) Uno(-(n % 6)) else Dos(n % 5)).toList
    val resultado = objAplicarMovs.aplicarMovimientos(e, movimientos)
    assert(resultado.nonEmpty)
    assert(objAplicarMovs.esUnico(resultado.last))
  }
}
