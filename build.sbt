lazy val SCALA_2 = "2.13.7"

lazy val `flusterstorm`: Project = project
  .enablePlugins(PackPlugin, BuildInfoPlugin)
  .in(file("."))
  .settings(
    name := "Flusterstorm",
    organizationName := "Milosz Jakubanis",
    version := "0.0.2-SNAPSHOT",
    scalaVersion := SCALA_2,
    organization := "com.miloszjakubanis",
    libraryDependencies ++= Seq(
      //Logback
      "ch.qos.logback" % "logback-classic" % "1.2.6",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",

      //Config
      "com.typesafe" % "config" % "1.4.1",
      "com.lihaoyi" %% "utest" % "0.7.10" % Test,
    ),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-unchecked",
      "-language:implicitConversions",
    ),
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),

    resolvers := Seq(
      "releases" at s"https://artifact.miloszjakubanis.com/repository/earth/",
      "snapshots" at s"https://artifact.miloszjakubanis.com/repository/moon/",
    ),
    //Credentials
    versionScheme := Some("early-semver"),
    publishMavenStyle := true,
    credentials += Credentials(new File(Path.userHome.absolutePath + "/.nexus/credentials")),
    publishTo := Some("Sonatype Nexus Repository Manager" at {
      if (isSnapshot.value)
        s"https://artifact.miloszjakubanis.com/repository/moon"
      else
        s"https://artifact.miloszjakubanis.com/repository/earth"
    })
  )