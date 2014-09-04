package de.leanovate.doby

class ExpirationApp extends App {

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

  // compilation warning
  // [warn] [...]/doby/sample/src/test/scala/de/leanovate/doby/ExpirationApp.scala:22: TODO from 'YaSi': 'change this date to be only a warning' will expired
  // [warn]   TODO("YaSi", "change this date to be only a warning", "2014/09/12")
  // [warn]       ^
  TODO("YaSi", "change this date to be only a warning", "2014/09/12")


  FIXME("YaSi", "bring peace to world", "2044/06/17")
}
