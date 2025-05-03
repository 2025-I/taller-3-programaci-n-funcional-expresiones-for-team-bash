package taller

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AplicarMovimientoTest extends AnyFunSuite {
  val objAplicarMo = new Operaciones()

  test("Aplicar movimientos según el ejemplo proporcionado") {
    val e1: (List[Any], List[Any], List[Any]) = (List('a', 'b', 'c', 'd'), Nil, Nil)
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

  test("Prueba de juguete con Int: listas de tamaño 10 y 10 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (List.range(1, 11), Nil, Nil)
    val movimientos = List(Uno(1), Dos(-1), Uno(2), Dos(1), Uno(-3), Dos(-2), Uno(1), Dos(2), Uno(-1), Dos(3))
    val resultado = movimientos.foldLeft(e)((estado, movimiento) => objAplicarMo.aplicarMovimiento(estado, movimiento))
    assert(objAplicarMo.esUnico(resultado))
  }

  test("Prueba pequeña con Int: listas de tamaño 100 y 100 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (List.range(1, 101), Nil, Nil)
    val movimientos = (1 to 100).map(n => if (n % 2 == 0) Uno(n % 5) else Dos(-(n % 3))).toList
    val resultado = movimientos.foldLeft(e)((estado, movimiento) => objAplicarMo.aplicarMovimiento(estado, movimiento))
    assert(objAplicarMo.esUnico(resultado))
  }

  test("Prueba mediana con Int: listas de tamaño 500 y 500 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (List.range(1, 501), Nil, Nil)
    val movimientos = (1 to 500).map(n => if (n % 3 == 0) Uno(n % 7) else Dos(-(n % 4))).toList
    val resultado = movimientos.foldLeft(e)((estado, movimiento) => objAplicarMo.aplicarMovimiento(estado, movimiento))
    assert(objAplicarMo.esUnico(resultado))
  }

  test("Prueba grande con Int: listas de tamaño 1000 y 1000 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (List.range(1, 1001), Nil, Nil)
    val movimientos = (1 to 1000).map(n => if (n % 2 == 0) Uno(-(n % 6)) else Dos(n % 5)).toList
    val resultado = movimientos.foldLeft(e)((estado, movimiento) => objAplicarMo.aplicarMovimiento(estado, movimiento))
    assert(objAplicarMo.esUnico(resultado))
  }
  //Movimientos con Char
  //Pruebas con Char
  test("Prueba de juguete con Char: listas de tamaño 10 y 10 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (('a' to 'j').toList, Nil, Nil)
    val movimientos = List(Uno(1), Dos(-1), Uno(2), Dos(1), Uno(-3), Dos(-2), Uno(1), Dos(2), Uno(-1), Dos(3))
    val resultado = movimientos.foldLeft(e)((estado, movimiento) => objAplicarMo.aplicarMovimiento(estado, movimiento))
    assert(objAplicarMo.esUnico(resultado))
  }

  test("Prueba pequeña con Char: listas de tamaño 100 y 100 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (('a' to 'z').toList, Nil, Nil)
    val movimientos = (1 to 100).map(n => if (n % 2 == 0) Uno(n % 5) else Dos(-(n % 3))).toList
    val resultado = movimientos.foldLeft(e)((estado, movimiento) => objAplicarMo.aplicarMovimiento(estado, movimiento))
    assert(objAplicarMo.esUnico(resultado))
  }

  test("Prueba mediana con Char: listas de tamaño 500 y 500 movimientos variados ") {
    val e: (List[Any], List[Any], List[Any]) = (('a' to 'z').flatMap(c => List(c)).take(500).toList, Nil, Nil)
    val movimientos = (1 to 500).map(n => if (n % 3 == 0) Uno(n % 7) else Dos(-(n % 4))).toList
    val resultado = movimientos.foldLeft(e)((estado, movimiento) => objAplicarMo.aplicarMovimiento(estado, movimiento))
    assert(objAplicarMo.esUnico(resultado))
  }

  test("Prueba grande con Char: listas de tamaño 1000 y 1000 movimientos variados") {
    val e: (List[Any], List[Any], List[Any]) = (('a' to 'z').flatMap(c => List(c)).take(1000).toList, Nil, Nil)
    val movimientos = (1 to 1000).map(n => if (n % 2 == 0) Uno(-(n % 6)) else Dos(n % 5)).toList
    val resultado = movimientos.foldLeft(e)((estado, movimiento) => objAplicarMo.aplicarMovimiento(estado, movimiento))
    assert(objAplicarMo.esUnico(resultado))
  }
}

