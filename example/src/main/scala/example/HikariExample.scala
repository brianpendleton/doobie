package doobie.example

import scalaz._, Scalaz._, scalaz.concurrent.Task

import doobie.imports._
import doobie.contrib.hikari.hikaritransactor._

object HikariExample {

  def tmain: Task[Unit] = 
    for {
      xa <- HikariXA[Task]("org.h2.Driver", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "")
      _  <- FirstExample.examples.transact[Task](xa)
      _  <- xa.shutdown[Task]
    } yield ()

  def main(args: Array[String]) =
    tmain.unsafePerformSync

}
