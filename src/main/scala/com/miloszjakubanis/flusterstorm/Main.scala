package com.miloszjakubanis.flusterstorm.Main

import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.miloszjakubanis.flusterstorm.{AbstractJob, PrintingJob}
import com.miloszjakubanis.flusterstorm.pipeline.{Pipeline, SimplePipeline}
import scala.concurrent.ExecutionContext
import com.miloszjakubanis.flusterstorm.Job.given_ExecutionContext

@main
def main =
  val job = new AbstractJob[String, Int](e => Future{println("step 1");Integer.parseInt(e)})
  val job2 = new AbstractJob[Int, Int](e => Future{println("step 2");e*e})
  val job3 = new PrintingJob[Int]("Result: ")

  val time = System.nanoTime()

  // val result = (job + job2 + job3)("3").onComplete {
  //   case Success(e) => println(s"Finished in: ${(System.nanoTime() - time) / 1000000000} seconds")
  //   case Failure(e) => throw new RuntimeException("Error happened")
  // }

  val pipeline = new SimplePipeline(job)
  val pipeline2 = new SimplePipeline(job2)
  val pipeline3 = new SimplePipeline(job3)

  val compo = pipeline + pipeline2 + pipeline3

  compo.inputData += "2"
  Thread.sleep(1000)

  compo().onComplete {
    case Success(e) => println(s"Finished in: ${(System.nanoTime() - time) / 1000000000} seconds")
    case Failure(e) => throw new RuntimeException("Error happened")
  }