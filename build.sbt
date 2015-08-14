
name := "scalajs-react-tutorial"

version := "1.0"

scalaVersion := "2.11.7"

enablePlugins(ScalaJSPlugin)

scalaJSStage in Global := FastOptStage

libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "0.9.1"

jsDependencies += "org.webjars" % "react" % "0.12.2" / "react-with-addons.js" commonJSName "React"

skip in packageJSDependencies := false