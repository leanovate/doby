package de.leanovate.doby

import Expiration._

class ExpirationApp extends App {

  expire("3001-01-01") // compile

  expire("2014-06-01") // compilation warning

  expire("2001-01-01") // does not compile

}
