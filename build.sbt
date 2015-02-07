name := "panoptes-storm"

scalaVersion := "2.11.4"

organization := "com.github.zooniverse"

libraryDependencies += "com.github.velvia" %% "scala-storm" % "0.2.5-SNAPSHOT"

libraryDependencies += "org.apache.storm" % "storm-core" % "0.9.3"

libraryDependencies += "org.apache.storm" % "storm-kafka" % "0.9.3"

libraryDependencies += "org.apache.kafka" %% "kafka" % "0.8.2.0" exclude("org.apache.zookeeper", "zookeeper") exclude("log4j", "log4j")

libraryDependencies += "io.spray" %%  "spray-json" % "1.3.1"

scalacOptions ++= Seq("-feature", "-deprecation", "-Yresolve-term-conflict:package")

// When doing sbt run, fork a separate process. This is apparently needed by storm.

fork := true

resolvers += "clojars" at "https://clojars.org/repo"

resolvers += "Maven central" at "http://repo1.maven.org/maven2/"

resolvers += Resolver.mavenLocal
