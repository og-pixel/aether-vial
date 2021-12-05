package com.miloszjakubanis.flusterstorm.pipeline

import com.miloszjakubanis.flusterstorm.job.Job

import java.util.concurrent.ConcurrentLinkedQueue
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future

class Pipeline[A, B](
  val job: Job[A, B]
) extends Runnable {


  val inputData: ConcurrentLinkedQueue[A] = new ConcurrentLinkedQueue()
  val outputData: ConcurrentLinkedQueue[Future[B]] = new ConcurrentLinkedQueue()

  override def run(): Unit =
    work()

  private[this] def work(): Unit = {
    do {
      val value = inputData.poll
      val res = job.apply(value)
      outputData.add(res)
    } while (!inputData.isEmpty)
  }

  def getFinishedResults(): Seq[B] = {
    val arr = new ArrayBuffer[B]()
    val iter = outputData.iterator()
    do {
      val future: Future[B] = iter.next()
      if (future.isCompleted) arr.addOne(future.value.get.get)
    } while (iter.hasNext)
    arr.toSeq
  }

  def getFutureResults(): Seq[Future[B]] = {
    val arr = new ArrayBuffer[Future[B]]()
    val iter = outputData.iterator()
    do {
      val future: Future[B] = iter.next()
      arr.addOne(future)
    } while (iter.hasNext)
    arr.toSeq
  }
}