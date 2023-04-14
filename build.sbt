name := "chemondis-etl"
version := "0.0.1"
scalaVersion := "3.2.2"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-target:jvm-1.8",
  "-encoding", "UTF-8",
  "-Xfuture"
  "-Ywarn-unused",
  "-Ywarn-unused-import"
)


lazy val versions = new {
  val postgres = ""
}
