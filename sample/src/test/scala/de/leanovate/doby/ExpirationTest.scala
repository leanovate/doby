package de.leanovate.doby

import org.scalatest.FunSuite
import de.leanovate.doby.Expiration._

class ExpirationTest extends FunSuite {
  
  test("an expiration date far in the future should be compiled") {
    expire("3014-05-03")
  }

  test("an expired date should not compile") {
    assertTypeError("""expire("2001-05-03")""")
  }

  test("a not expired TODO should compile") {
    TODO("YaSi", "use other date formats", "3014-05-03")
  }

  test("a not expired FIXME should compile") {
    FIXME("YaSi", "use other date formats", "3014-05-03")
  }

  test("a not valid date cannot be compiled") {
    assertTypeError(
      """ FIXME("YaSi", "use other date formats", "abc") """)
  }

  test("an expired TODO should compile") {
    assertTypeError(
      """ TODO("YaSi", "use other date formats", "2001-05-03") """)
  }

  test("an expired TODO can use the date format yyyy/MM/dd") {
    assertTypeError(
      """ TODO("YaSi", "use other date formats", "2001/05/03") """)
  }

}
