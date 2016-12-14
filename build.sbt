name := "FinalProject"


version := "1.0.0-SNAPSHOT"

scalaVersion := "2.11.8"

val scalaTestVersion = "2.2.4"

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

//EclipseKeys.withSource := true

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % "1.0.4",
  "org.spire-math" %% "spire" % "0.10.1",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
  "org.apache.commons" % "commons-math3" % "3.6",
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  "com.typesafe.play" % "play-json_2.11" % "2.5.1",
  "org.apache.commons" % "commons-lang3" % "3.4",

  "org.apache.spark" % "spark-core_2.11" % "1.6.1",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.7.2",
  "com.norbitltd" % "spoiwo" % "1.0.6",
  "org.apache.spark" % "spark-mllib_2.11" % "1.6.1",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.7.2",

  "com.databricks" % "spark-csv_2.11" % "1.5.0"
)