name := "doby"

version := "0.0.1-SNAPSHOT"

organization := "de.leanovate.doby"

scalaVersion := "2.11.0"

lazy val code = project.in(file("project-code"))
  .settings(scalaVersion := "2.11.0")
  .settings(libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value)

lazy val sample = project.in(file("sample"))
  .settings(scalaVersion := "2.11.0")
  .dependsOn(code)

lazy val root = project.in(file("."))
  .settings(scalaVersion := "2.11.0")
  .settings(publishArtifact := false)
  .aggregate(code, sample)