import com.typesafe.sbt.pgp.PgpKeys._
import sbt._
import sbt.Keys._
import sbtrelease.ReleasePlugin.ReleaseKeys._
import sbtrelease.ReleaseStateTransformations._
import sbtrelease.ReleaseStep
import xerial.sbt.Sonatype.SonatypeKeys._

object Common {

  lazy val publisSignedArtifactsAction = {
    st: State =>
      val extracted = Project.extract(st)
      val ref = extracted.get(thisProjectRef)
      extracted.runAggregated(publishSigned in Global in ref, st)
  }

  val settings =
    xerial.sbt.Sonatype.sonatypeSettings ++
      com.typesafe.sbt.SbtPgp.settings ++
      Release.settings ++ Seq(
    organization := "de.leanovate.doby",
    scalaVersion := "2.11.0"
  )

}