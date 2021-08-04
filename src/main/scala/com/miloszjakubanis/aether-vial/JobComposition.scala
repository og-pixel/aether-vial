package com.miloszjakubanis.`aether-vial`

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class JobComposition[A, B, C](
    val left: Job[A, B],
    val right: Job[B, C],
) extends Job[A, C]:

  override def apply(a: A): Future[C] =
    for {
      b <- left(a)
      c <- right(b)
    } yield c