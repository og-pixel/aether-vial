package com.miloszjakubanis.flusterstorm.pipeline

import com.miloszjakubanis.flusterstorm.pipeline.PipelineComposition
import com.miloszjakubanis.flusterstorm.Job

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{Await, Future}

/** `Wrapper` around `Job` class
 * that contains storage for jobs to be
 * continuously processed
 * */
trait Pipeline[A, B]:

  val job: Job[A, B]

  val inputData: ArrayBuffer[A]
  val outputData: ArrayBuffer[B]

  def compose[C](pipe: Pipeline[B, C]): Pipeline[A, C] =
    new PipelineComposition(this, pipe)

  def +[C](pipe: Pipeline[B, C]): Pipeline[A, C] =
    compose(pipe)

  /**
   * Default Method to run the pipeline
   */
  def apply(): Future[B]

  def submitInputData(a: A): Unit = inputData += a
