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
import scala.reflect.ClassTag

class Pipeline[A, B](
  val job: Job[A, B]
) extends Runnable:

  val inputData: ConcurrentLinkedQueue[A] = new ConcurrentLinkedQueue()
  val outputData: ConcurrentLinkedQueue[Future[B]] = new ConcurrentLinkedQueue()

  override def run(): Unit =
    work()

  private[this] def work(): Unit =
    while !inputData.isEmpty do
      val value = inputData.poll.nn
      val res = job.apply(value)
      outputData.add(res)

  def getFinishedResults(): Seq[B] =
    val arr = new ArrayBuffer[B]()
    val iter = outputData.iterator().nn
    while iter.hasNext() do
      val future: Future[B] = iter.next().nn
      if future.isCompleted then
        arr.addOne(future.value.get.get)
    arr.toSeq

  def getFutureResults(): Seq[Future[B]] =
    val arr  = new ArrayBuffer[Future[B]]()
    val iter = outputData.iterator().nn
    while iter.hasNext() do
      val future: Future[B] = iter.next().nn
      arr.addOne(future)
    arr.toSeq