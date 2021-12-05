lazy val SCALA_3 = "3.0.1"
lazy val SCALA_2 = "2.13.7"

lazy val `flusterstorm`: Project = Project("flusterstorm", file("."))
//  .enablePlugins(MdocPlugin)
  .enablePlugins(PackPlugin)
  .enablePlugins(BuildInfoPlugin)
  .settings(
    name := "Flusterstorm",
    organizationName := "Milosz Jakubanis",
    version := "0.0.1",
    scalaVersion := SCALA_2,
    organization := "com.miloszjakubanis",
    //TODO doesn't work
    // packMain := Map("main" -> "com.miloszjakubanis.aether-vial.Main"),
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.1",
      "com.lihaoyi" %% "utest" % "0.7.10" % Test,
    ),
    testFrameworks += new TestFramework("utest.runner.Framework"),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-unchecked",
//      "-Yexplicit-nulls",
//      "-Ysafe-init",
//      "-new-syntax",
      //Extra language options
//      "-language:dynamics",             // Allow direct or indirect subclasses of scala.Dynamic
//      "-language:existential",          // Existential types (besides wildcard types) can be written and inferred
//      "-language:experimental.macros",  // Allow macro defintion (besides implementation and application)
//      "-language:higherKinds",          // Allow higher-kinded types
//      "-language:implicitConversions",  // Allow definition of implicit functions called views
//      "-language:postfixOps",           // Allow postfix operator notation, such as `1 to 10 toList'
//      "-language:reflectiveCalls",      // Allow reflective access to members of structural types
    ),
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
  )
