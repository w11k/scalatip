import sbt._

class Project(info: ProjectInfo) extends DefaultWebProject(info) with AkkaProject {

  // Compile dependencies
  override val akkaActor = akkaModule("actor").withSources
  val akkaHttp = akkaModule("http").withSources
  val liftWebkit = "net.liftweb" %% "lift-webkit" % "2.2" withSources
  val liftActor = "net.liftweb" %% "lift-actor" % "2.2" withSources // Just for the sources!
  val liftCommon = "net.liftweb" %% "lift-common" % "2.2" withSources // Just for the sources!
  val liftUtil = "net.liftweb" %% "lift-util" % "2.2" withSources // Just for the sources!
  val databinder = "net.databinder" %% "dispatch-twitter" % "0.7.8" withSources
  val logback = "ch.qos.logback" % "logback-classic" % "0.9.28"
  val casbah = "com.mongodb.casbah" %% "casbah" % "2.0.2"

  // Test dependencies
  val mockito = "org.mockito" % "mockito-all" % "1.8.5" % "test"
  val scalaCheck = "org.scala-tools.testing" %% "scalacheck" % "1.8" % "test" withSources
  val specs = "org.scala-tools.testing" %% "specs" % "1.6.7.1" % "test" withSources
  val jettyWebapp = "org.eclipse.jetty" % "jetty-webapp" % "7.0.2.v20100331" % "test"

  // Webapp settings for JRebel
  override def jettyWebappPath = webappPath
  override def scanDirectories = Nil
}
