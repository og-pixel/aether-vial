package com.miloszjakubanis.`aether-vial`

import scala.concurrent.{Future, Await} 
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

given scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

trait Job[A, B]:
  val fun: A => Future[B]
  val input: A

  def handleResults[C](a: B => C) = fun(input).onComplete {
      case Success(value) => a(value) 
      case Failure(e) => e.printStackTrace
  }