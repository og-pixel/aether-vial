package com.miloszjakubanis.flusterstorm.job

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global



//TODO remove or do something about global execution context
object Job {
  //TODO make a package object about it
  type AnyJob = Job[_, _]

}

trait Job[A, B] extends Function1[A, Future[B]] {

  //TODO better idea?
  implicit val ec: ExecutionContext = global

  def compose[Z <: Job[B, C], C](job: Z): Job[A, C] =
    new JobComposition(this, job)

  def +[Z <: Job[B, C], C](job: Job[B, C]): Job[A, C] =
    compose(job)
}
