name := "parent"

Common.settings

lazy val code = project.in(file("project-code"))

lazy val sample = project.in(file("sample"))
  .dependsOn(code)

lazy val root = project.in(file("."))
  .settings(publishArtifact := false)
  .aggregate(code, sample)