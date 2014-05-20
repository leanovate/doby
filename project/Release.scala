import sbt._
import sbt.Keys._
import sbtrelease.ReleasePlugin._

object Release {
  val settings = releaseSettings ++ Seq(
    publishTo := Some(Resolver.defaultLocal),

    // remove when issue fixed: https://github.com/sbt/sbt-release/issues/70
    crossScalaVersions := Seq(scalaVersion.value)
  )
}