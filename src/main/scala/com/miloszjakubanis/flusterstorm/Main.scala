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

import scala.concurrent.{ Future, Promise }
import java.util.concurrent.ConcurrentLinkedQueue

@main
def main =
  val job = new AbstractJob[String, Int](e => Future{Thread.sleep(3000); Integer.parseInt(e)})
  val job2 = new AbstractJob[Int, Int](e => Future(e * e))
  val job3 = new AbstractJob[Int, Double](e => Future(e / 2.3))
  val job4 = new PrintingJob[Double]()

  val time = System.nanoTime()
  val aa = job + job2 + job3 + job4

  val pipeline = new Pipeline(aa)

  pipeline.inputData.add("1")
  pipeline.inputData.add("2")
  pipeline.work()