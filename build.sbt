name := "PriceCheckerClient"

version := "1.0"

scalaVersion := "2.12.12"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

libraryDependencies ++= Seq(
    "org.scalafx" %% "scalafx" % "8.0.192-R14",
    "org.scalafx" %% "scalafxml-core-sfx8" % "0.5",
)