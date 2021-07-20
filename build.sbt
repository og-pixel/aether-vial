lazy val SCALA_3 = "3.0.0"

lazy val `aether-vial`: Project = Project("aether-vial", file("."))
//  .enablePlugins(MdocPlugin)
  .settings(
    name := "Aether Vial",
    organizationName := "Milosz Jakubanis",
    version := "0.0.1",
    scalaVersion := SCALA_3,
    organization := "com.miloszjakubanis",
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.1",
      "com.lihaoyi" %% "utest" % "0.7.10" % Test,
    ),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    scalacOptions ++= Seq(
      "-feature",
      "-language:implicitConversions",
      "-deprecation",
      "-unchecked",
      "-Yexplicit-nulls",
      "-Ysafe-init",
      "-new-syntax",
    )
  )