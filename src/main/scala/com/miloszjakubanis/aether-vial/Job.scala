package com.miloszjakubanis.`aether-vial`

import scala.concurrent.{Future, Await} 
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

given scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global
trait Job[A, B]:

  val fun: A => Future[B]
  val input: A

  def apply(): Future[B] = fun(input)

  def apply[C](a: B => C): Unit = 
    apply()
    fun(input).onComplete {
      case Success(value) => a(value) 
      case Failure(e) => e.printStackTrace
    }

  //TODO this might be too simplistic
  def awaitResult(): B = Await.result(fun(input), Duration("10 seconds"))