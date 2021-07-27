package com.miloszjakubanis.`aether-vial`

import java.util.concurrent.{ExecutorService, Executors}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}
import scala.concurrent.duration.Duration
import scala.concurrent.CanAwait
import com.miloszjakubanis.`aether-vial`.JobWithInput

open class AbstractJob[A, B](a: A)(val fun: A => Future[B])
    extends JobWithInput[A, B](a):

  override def apply(): Future[B] = fun(a)

  override def apply[C](b: B => C): Unit =
    apply()
    fun(a).onComplete {
      case Success(value) => b(value)
      case Failure(e)     => e.printStackTrace
    }

  override def run(): Unit = apply()
