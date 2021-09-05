package com.miloszjakubanis.flusterstorm.pipeline

import com.miloszjakubanis.flusterstorm.pipeline.Pipeline

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future

class PipelineComposition[A, C, B](
    val left: Pipeline[A, C],
    val right: Pipeline[C, B]
) extends Pipeline[A, B]:

  override val job = left.job + right.job

  override val inputData: ArrayBuffer[A] = left.inputData
  override val outputData: ArrayBuffer[B] = right.outputData

  val middleData: ArrayBuffer[C] = ArrayBuffer()

  override def apply(): Future[B] = synchronized {
    left.apply()
    right.apply()
  }