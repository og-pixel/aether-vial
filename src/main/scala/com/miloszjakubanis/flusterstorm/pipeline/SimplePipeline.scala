package com.miloszjakubanis.flusterstorm.pipeline

import com.miloszjakubanis.flusterstorm.pipeline.Pipeline
import com.miloszjakubanis.flusterstorm.job.Job
import scala.concurrent.Future
import scala.collection.mutable.ArrayBuffer

/**
 * Simple Pipeline implementation going input->output
 */
class SimplePipeline[A, B](val job: Job[A, B]) extends Pipeline[A, B]:

  override val inputData: ArrayBuffer[A] = new ArrayBuffer()

  override val outputData: ArrayBuffer[B] = new ArrayBuffer()

  // def apply(): Future[B] = job(inputData.head)