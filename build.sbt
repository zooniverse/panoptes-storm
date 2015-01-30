name := "panoptes-storm"

scalaVersion := "2.11.4"

organization := "com.github.zooniverse"

libraryDependencies += "com.github.velvia" % "scala-storm_2.11" % "0.2.5-SNAPSHOT"

scalacOptions ++= Seq("-feature", "-deprecation", "-Yresolve-term-conflict:package")

// When doing sbt run, fork a separate process. This is apparently needed by storm.

fork := true

resolvers += "clojars" at "https://clojars.org/repo"

resolvers += Resolver.mavenLocal

