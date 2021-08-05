package com.miloszjakubanis.flusterstorm

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

import com.miloszjakubanis.flusterstorm.Job.given_ExecutionContext

class JobComposition[A, C, B](
    val left: Job[A, C],
    val right: Job[C, B],
) extends Job[A, B]:

  override def apply(a: A): Future[B] =
    for {
      b <- left(a)
      c <- right(b)
    } yield c
