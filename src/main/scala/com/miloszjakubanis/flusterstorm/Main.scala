package com.miloszjakubanis.flusterstorm.Main

import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.miloszjakubanis.flusterstorm.job.PrintingJob
import com.miloszjakubanis.flusterstorm.pipeline.{Pipeline, SimplePipeline}

import scala.concurrent.ExecutionContext
import com.miloszjakubanis.flusterstorm.job.Job.given_ExecutionContext
import com.miloszjakubanis.flusterstorm.job.{AbstractJob, PrintingJob}
import com.miloszjakubanis.flusterstorm.user.AbstractUser

@main
def main =
  val job = new AbstractJob[String, Int](e => Future(Integer.parseInt(e)))
  val job2 = new AbstractJob[Int, Int](e => Future(e * e))
  val job3 = new AbstractJob[Int, Double](e => Future(e / 2.3))
  val job4 = new PrintingJob[Double]()
//  val job4 = new SimpleJob[Double, Double](20, e => Future{println(s"Step 4 $e"); e + 1})
//  val job5 = new SimpleJob[Double, Double](20, e => Future{println(s"Step 5 $e"); e + 5})

  val time = System.nanoTime()

  val aa = job + job2 + job3 + job4 //+ job5

  val res = aa.apply("2")
  Thread.sleep(1000)
  println(res.value.get)