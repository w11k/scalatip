import sbt._

class Project(info: ProjectInfo) extends DefaultProject(info) {

  // Compile dependencies

  // Test dependencies
  val mockito = "org.mockito" % "mockito-all" % "1.8.5" % "test"
  val scalaCheck = "org.scala-tools.testing" %% "scalacheck" % "1.8" % "test" withSources
  val specs = "org.scala-tools.testing" %% "specs" % "1.6.7.1" % "test" withSources
}
