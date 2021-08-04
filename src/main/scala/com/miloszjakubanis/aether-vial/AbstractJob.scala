package com.miloszjakubanis.`aether-vial`

import java.util.concurrent.{ExecutorService, Executors}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}
import scala.concurrent.duration.Duration
import scala.concurrent.CanAwait

//TODO I called it `abstract` in a sense of functional programming
// I hope it makes sense
open class AbstractJob[A, B](fun: A => Future[B])
    extends Job[A, B]:

  override def apply(a: A): Future[B] = fun(a)