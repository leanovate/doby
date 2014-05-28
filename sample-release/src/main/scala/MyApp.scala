import de.leanovate.doby.Expiration._

object MyApp extends App {

  expire("3001-01-01") // compile

  // compilation warning
  // [warn] [...]/doby/sample/src/test/scala/de/leanovate/doby/ExpirationApp.scala:10: will expired
  // [warn]   expire("3001-01-01", "2001-01-01")
  // [warn]         ^
  expire("3001-01-01", "2001-01-01")

  // does not compile
  // [error] [...]/doby/sample/src/test/scala/de/leanovate/doby/ExpirationApp.scala:16: has expired
  // [error]   expire("2001-01-01")
  // [error]         ^
  //expire("2001-01-01")

  FIXME("YaSi", "bring peace to world", "2044/06/17")
}
