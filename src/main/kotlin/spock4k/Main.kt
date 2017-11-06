package spock4k

fun main(args: Array<String>) {
  DataTableTest.apply {
    `square ints`.forEach { it() }
    `multiply ints`.forEach { it() }
  }
}
