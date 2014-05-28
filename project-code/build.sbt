name := "doby"

Common.settings

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

libraryDependencies ++= (
  if (scalaVersion.value.startsWith("2.10")) List("org.scalamacros" %% "quasiquotes" % Common.paradiseVersion)
  else Nil
)