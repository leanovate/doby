import sbt.Keys._

object Common {

  val settings =
    Release.settings ++ Seq(
      organization := "de.leanovate.doby",
      scalaVersion := "2.11.2"
    )

}