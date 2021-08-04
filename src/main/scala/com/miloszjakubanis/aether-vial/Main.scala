package com.miloszjakubanis.`aether-vial`.Main

import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.miloszjakubanis.`aether-vial`.AbstractJob

given scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

@main def main =
  val job = new AbstractJob("",1)(e => Future{Thread.sleep(2000);Integer.parseInt(e)})
  val job2 = new AbstractJob(1,1)(e => Future{Thread.sleep(2000);e*e})
  val job3 = new AbstractJob(1,1.2)(e => Future{Thread.sleep(2000);e/3.2})

  val time = System.nanoTime()

  (job + job2 + job3)("3").onComplete {
    case Success(e) => println(s"Finished in: ${(System.nanoTime() - time) / 1000000000} seconds")
    case Failure(e) => throw new RuntimeException("Error happened")
  }

  val result = System.nanoTime() - time