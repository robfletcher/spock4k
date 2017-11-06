package spock4k.table.v1

import org.funktionale.partials.partially1

object DataTableTest {
  val `square ints` =
    test { n: Int, expected: Int ->
      println("$n * $n == $expected")
      assert(n * n == expected)
    } where {
      listOf(
        2 col 4,
        4 col 16
      )
    }

  val `multiply ints` =
    test { n1: Int, n2: Int, expected: Int ->
      println("$n1 * $n2 == $expected")
      assert(n1 * n2 == expected)
    } where {
      listOf(
        2 col 4 col 8,
        4 col 2 col 8
      )
    }
}

private fun <P1, P2> test(body: (P1, P2) -> Unit): ParametrizedTest2<P1, P2> =
  ParametrizedTest2(body)

private infix fun <P1, P2> ParametrizedTest2<P1, P2>.where(
  table: () -> Iterable<Pair<P1, P2>>
): Iterable<() -> Unit> =
  table()
    .map { row ->
      this@where
        .partially1(row.first)
        .partially1(row.second)
    }

private class ParametrizedTest2<in P1, in P2>(body: (P1, P2) -> Unit)
  : (P1, P2) -> Unit by body

private infix fun <P1, P2> P1.col(rhs: P2): Pair<P1, P2> =
  Pair(this, rhs)

private fun <P1, P2, P3> test(body: (P1, P2, P3) -> Unit): ParametrizedTest3<P1, P2, P3> =
  ParametrizedTest3(body)

private infix fun <P1, P2, P3> ParametrizedTest3<P1, P2, P3>.where(
  table: () -> Iterable<Triple<P1, P2, P3>>
): Iterable<() -> Unit> =
  table()
    .map { row ->
      this@where
        .partially1(row.first)
        .partially1(row.second)
        .partially1(row.third)
    }

private class ParametrizedTest3<in P1, in P2, in P3>(body: (P1, P2, P3) -> Unit)
  : (P1, P2, P3) -> Unit by body

private infix fun <P1, P2, P3> Pair<P1, P2>.col(rhs: P3): Triple<P1, P2, P3> =
  Triple(this.first, this.second, rhs)
