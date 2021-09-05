package com.miloszjakubanis.flusterstorm.job

import java.util.concurrent.{ExecutorService, Executors}
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.{CanAwait, ExecutionContext, Future}
import scala.util.{Failure, Success}

//TODO I called it `abstract` in a sense of functional programming
// I hope it makes sense
class AbstractJob[A, B](fun: A => Future[B])
    extends Job[A, B] with Function1[A, Future[B]]:

  override def apply(a: A): Future[B] = fun(a)