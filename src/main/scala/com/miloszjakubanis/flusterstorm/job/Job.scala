package com.miloszjakubanis.flusterstorm.job

import com.miloszjakubanis.flusterstorm.job.Job.given_ExecutionContext
import com.miloszjakubanis.flusterstorm.job.JobComposition

import scala.collection.Iterable
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

//TODO make a package object about it
type AnyJob = Job[_, _]

//TODO remove or do something about global execution context
object Job:
  given ExecutionContext = ExecutionContext.global

trait Job[A, B] extends Function1[A, Future[B]]:

  def compose[Z <: Job[B, C], C](job: Z): Job[A, C] =
    new JobComposition(this, job)

  def +[Z <: Job[B, C], C](job: Job[B, C]): Job[A, C] =
    compose(job)