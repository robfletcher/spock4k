package spock4k.table.v3

import org.funktionale.partials.partially1

object DataTableTest {
  fun `square 2`() =
    expect {
      assert(2 * 2 == 4)
    }

  fun `square ints`() =
    expect { n: Int, expected: Int ->
      println("$n * $n == $expected")
      assert(n * n == expected)
    } where {
      data(2, 4)
      data(4, 16)
    }
}

private fun expect(body: Test) {
  body()
}

typealias Test = () -> Unit

typealias Test2<P1, P2> = (P1, P2) -> Unit

private fun <P1, P2> expect(body: Test2<P1, P2>): Test2<P1, P2> = body

private interface Supplier2<in P1, in P2> {
  fun data(p1: P1, p2: P2)
}

private infix fun <P1, P2> Test2<P1, P2>.where(
  table: Supplier2<P1, P2>.() -> Unit
) {
  val tests = mutableListOf<Test>()
  with(object : Supplier2<P1, P2> {
    override fun data(p1: P1, p2: P2) {
      tests += this@where
        .partially1(p1)
        .partially1(p2)
    }
  }) {
    table()
  }
  tests.forEach { it() }
}
