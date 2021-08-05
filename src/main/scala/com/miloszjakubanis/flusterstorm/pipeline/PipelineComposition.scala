package com.miloszjakubanis.flusterstorm.pipeline

import com.miloszjakubanis.flusterstorm.{AbstractJob, Job}
import scala.concurrent.{ExecutionContext, Future}
import scala.collection.mutable.ArrayBuffer

class PipelineComposition[A, C, B](
    val left: Pipeline[A, C],
    val right: Pipeline[C, B]
) extends Pipeline[A, B]:

  override val job: Job[A, B] = left.job + right.job

  override val inputData: ArrayBuffer[A] = left.inputData

  override val outputData: ArrayBuffer[B] = right.outputData

  override def apply(): Future[B] = job(inputData.head)