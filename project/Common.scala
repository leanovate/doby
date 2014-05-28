import sbt._
import sbt.Keys._

object Common {

  val paradiseVersion = "2.0.0"

  val settings =
    Release.settings ++ Seq(
      organization := "de.leanovate.doby",
      scalaVersion := "2.11.1",
      crossScalaVersions := Seq("2.10.2", "2.10.3", "2.10.4", "2.11.0", "2.11.1"),
      addCompilerPlugin("org.scalamacros" % "paradise" % paradiseVersion cross CrossVersion.full)
    )

}
