import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies += scalaTest % Test,
    scalacOptions ++= Seq(
	"-Ywarn-infer-any",       
	"-Xlint:infer-any",
	"-Xlint:type-parameter-shadow",      // A local type parameter shadows a type already in scope.
  	"-Xlint:unsound-match"           // Pattern match may not be typesafe.
	)
  )
