package com.miloszjakubanis.flusterstorm.pipeline

import com.miloszjakubanis.flusterstorm.pipeline.Pipeline

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future
import scala.util.{Success, Failure}
import com.miloszjakubanis.flusterstorm.job.Job.given_ExecutionContext

import java.util.concurrent.ConcurrentLinkedQueue

class PipelineComposition[A, C, B](
    val left: Pipeline[A, C],
    val right: Pipeline[C, B]
) extends Pipeline[A, B]:

  override val job = left.job + right.job

  var inputData: ConcurrentLinkedQueue[A] = left.inputData
  var outputData: ConcurrentLinkedQueue[B] = right.outputData
  val middleData: ConcurrentLinkedQueue[C] = new ConcurrentLinkedQueue()

  left.outputData = middleData
  right.inputData = middleData

  override def apply(): Future[B] = synchronized {
    left.apply()
    right.apply()
  }