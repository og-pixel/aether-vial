package com.miloszjakubanis.flusterstorm.pipeline

import scala.collection.mutable.ArrayBuffer
import com.miloszjakubanis.flusterstorm.job.Job
import com.miloszjakubanis.flusterstorm.measureTime
import com.miloszjakubanis.flusterstorm.job.Job.given_ExecutionContext

import java.util.concurrent.ConcurrentLinkedQueue
import scala.concurrent.Future
import scala.util.{Failure, Success}
import java.time.Duration
import scala.concurrent.Promise

class Pipeline[A, B](
  val job: Job[A, B]
):

  val inputData: ConcurrentLinkedQueue[A] = new ConcurrentLinkedQueue()
  val outputData: ConcurrentLinkedQueue[Future[B]] = new ConcurrentLinkedQueue()

  def work(): Unit = 
    while !inputData.isEmpty do
      val value = inputData.poll.nn
      val res = job.apply(value)
      outputData.add(res)