package com.miloszjakubanis.flusterstorm.Main

import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.miloszjakubanis.flusterstorm.job.PrintingJob
import com.miloszjakubanis.flusterstorm.pipeline.*

import scala.concurrent.ExecutionContext
import com.miloszjakubanis.flusterstorm.job.Job.given_ExecutionContext
import com.miloszjakubanis.flusterstorm.job.{AbstractJob, PrintingJob}
import com.miloszjakubanis.flusterstorm.user.AbstractUser

import scala.collection.mutable.ArrayBuffer

@main
def main =
  val job = new AbstractJob[String, Int](e => Future(Integer.parseInt(e)))
  val job2 = new AbstractJob[Int, Int](e => Future(e * e))
  val job3 = new AbstractJob[Int, Double](e => Future(e / 2.3))
  val job4 = new PrintingJob[Double]()

  val time = System.nanoTime()
  val aa = job + job2 + job3

  val pipeline = new AbstractPipeline(job)
  val pipeline2 = new AbstractPipeline(job2)
  val pipeline3 = new AbstractPipeline(job3)

  val composition: Pipeline[String, Double] = pipeline + pipeline2 + pipeline3

  composition.inputData.add("1")
  val res = composition.apply()

  res.onComplete(e => e match {
    case Success(data) => println(s"FOUND DATA: $data")
    case Failure(e) => e.printStackTrace()
  })



//  val res = aa.apply("2")
//  Thread.sleep(1000)
//  println(res.value.get)

