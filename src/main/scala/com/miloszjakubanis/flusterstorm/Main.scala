package com.miloszjakubanis.flusterstorm.Main

import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import com.miloszjakubanis.flusterstorm.job.{AbstractJob, PrintingJob, StorageJob}
import com.miloszjakubanis.flusterstorm.pipeline.Pipeline

import scala.concurrent.ExecutionContext
import com.miloszjakubanis.flusterstorm.job.Job.given_ExecutionContext
import com.miloszjakubanis.flusterstorm.user.{AbstractUser, User, UserFactory}

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{Future, Promise}
import java.util.concurrent.ConcurrentLinkedQueue

@main
def main =
  val job = new AbstractJob[String, Int](e => Future{Integer.parseInt(e)})
  val job2 = new AbstractJob[Int, Int](e => Future(e * e))
  val job3 = new AbstractJob[Int, Double](e => Future(e / 2.3))
//  val job4 = new PrintingJob[Double]()
  val job4 = new StorageJob[Double, Double](e => Future(e + e))
  val job5 = new PrintingJob[Double]()

  val time = System.nanoTime()
  val aa = job + job2 + job3 + job4 + job5

  val pipeline = new Pipeline(aa)

  pipeline.inputData.add("1")
  pipeline.inputData.add("2")
  val a = new Thread(pipeline)

  val factory = UserFactory()
  val user = factory("milosz")
  user.submitPipeline(pipeline)
  user.runPipeline()

  Thread.sleep(10)
  println(user.pipeline)
  println(user.pipeline.getFinishedResults())