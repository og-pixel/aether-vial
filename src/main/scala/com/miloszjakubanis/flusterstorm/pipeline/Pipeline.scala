package com.miloszjakubanis.flusterstorm.pipeline

import scala.collection.mutable.ArrayBuffer
import com.miloszjakubanis.flusterstorm.job.Job
import com.miloszjakubanis.flusterstorm.job.Job.given_ExecutionContext

import scala.concurrent.Future
import scala.util.{Failure, Success}

trait Pipeline[A, B]:

  val job: Job[A, B]
  val inputData: ArrayBuffer[A] = ArrayBuffer()
  val outputData: ArrayBuffer[B] = ArrayBuffer()

  //TODO not sure of synchronized part
  def apply(): Future[B] = synchronized {
    val input = inputData.remove(0)
    inputData.trimToSize()
    val res = job(input)
    res.onComplete(e => e match {
      case Failure(e) => e.printStackTrace()
      case Success(data) => outputData.addOne(data)
    })
    res
  }

  def compose[Z <: Pipeline[B, C], C](pipeline: Z): Pipeline[A, C] =
    new PipelineComposition(this, pipeline)

  def +[Z <: Pipeline[B, C], C](pipeline: Pipeline[B, C]): Pipeline[A, C] =
    compose(pipeline)