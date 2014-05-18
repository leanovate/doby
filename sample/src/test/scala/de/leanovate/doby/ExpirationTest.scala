package de.leanovate.doby

import org.scalatest.FunSuite
import Expiration._

class ExpirationTest extends FunSuite {
  
  test("an expiration date far in the future should be compiled") {
    expire("3014-05-03")
  }

  test("an expired date should not compile") {
    assertTypeError("""expire("2001-05-03")""")
  }

}
