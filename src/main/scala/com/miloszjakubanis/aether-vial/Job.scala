package com.miloszjakubanis.`aether-vial`

import scala.concurrent.{Future, Await} 
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext

given scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global
trait Job[A, B] extends Runnable:

  val fun: A => Future[B]

  def apply(): Future[B]

  def apply[C](a: B => C): Unit

  // def complete: Boolean = if result != null then true else false