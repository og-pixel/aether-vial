package com.miloszjakubanis.flusterstorm

import scala.concurrent.{Future, Await}
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext
import scala.collection.Iterable

object Job:
  given ExecutionContext = ExecutionContext.global

trait Job[A, B] extends Function1[A, Future[B]]:

  def compose[C](job: Job[B, C]): Job[A, C] =
    new JobComposition(this, job)

  def +[C](job: Job[B, C]): Job[A, C] =
    compose(job)