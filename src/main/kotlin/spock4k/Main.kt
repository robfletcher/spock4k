package spock4k

import spock4k.table.v1.DataTableTest as DataTableTestV1
import spock4k.table.v2.DataTableTest as DataTableTestV2
import spock4k.table.v3.DataTableTest as DataTableTestV3

fun main(args: Array<String>) {
  with(DataTableTestV1) {
    `square ints`.forEach { it() }
    `multiply ints`.forEach { it() }
  }
  with(DataTableTestV2) {
    `square ints`.forEach { it() }
    `multiply ints`.forEach { it() }
  }
  with(DataTableTestV3) {
    `square 2`()
    `square ints`()
  }
}
