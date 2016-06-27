name := "SparkCount"

version := "1.0"

scalaVersion := "2.11.8"

// Apache Spark dependencies
libraryDependencies ++= Seq("org.apache.spark" % "spark-core_2.11" % "1.6.1",
  "org.apache.spark" % "spark-sql_2.11" % "1.6.1")

// SLF4J dependencies
libraryDependencies ++= Seq("org.slf4j" % "slf4j-api" % "1.7.21",
  "org.slf4j" % "slf4j-simple" % "1.7.21")
    