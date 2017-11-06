package spock4k.table.v2

import org.funktionale.partials.partially1

object DataTableTest {
  val `square ints` =
    test { n: Int, expected: Int ->
      println("$n * $n == $expected")
      assert(n * n == expected)
    } where {
      data(2, 4)
      data(4, 16)
    }

  val `multiply ints` =
    test { n1: Int, n2: Int, expected: Int ->
      println("$n1 * $n2 == $expected")
      assert(n1 * n2 == expected)
    } where {
      data(2, 4, 8)
      data(4, 2, 8)
    }
}

private fun <P1, P2> test(body: (P1, P2) -> Unit): (P1, P2) -> Unit = body

private infix fun <P1, P2> ((P1, P2) -> Unit).where(
  table: Parameters2<P1, P2>.() -> Unit
): Iterable<() -> Unit> =
  Parameters2(this).run {
    table()
    toTestSuite()
  }

private class Parameters2<in P1, in P2>(private val body: (P1, P2) -> Unit) {

  private val tests = mutableListOf<() -> Unit>()

  fun data(p1: P1, p2: P2) {
    tests.add(body.partially1(p1).partially1(p2))
  }

  fun toTestSuite(): Iterable<() -> Unit> = tests.toList()
}

private fun <P1, P2, P3> test(body: (P1, P2, P3) -> Unit): (P1, P2, P3) -> Unit = body

private infix fun <P1, P2, P3> ((P1, P2, P3) -> Unit).where(
  table: Parameters3<P1, P2, P3>.() -> Unit
): Iterable<() -> Unit> =
  Parameters3(this).run {
    table()
    toTestSuite()
  }

private class Parameters3<in P1, in P2, in P3>(private val body: (P1, P2, P3) -> Unit) {

  private val tests = mutableListOf<() -> Unit>()

  fun data(p1: P1, p2: P2, p3: P3) {
    tests.add(body.partially1(p1).partially1(p2).partially1(p3))
  }

  fun toTestSuite(): Iterable<() -> Unit> = tests.toList()
}
