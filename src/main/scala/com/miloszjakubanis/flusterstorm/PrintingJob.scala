package com.miloszjakubanis.flusterstorm

import com.miloszjakubanis.flusterstorm.Job

import scala.concurrent.Future

import com.miloszjakubanis.flusterstorm.Job.given_ExecutionContext
/**
 * Prints result to the console as soon as possible
 * @tparam A Any parameter that can be printer
 */
class PrintingJob[A](context: String = "") extends Job[A, Unit]:
  override def apply(a: A): Future[Unit] = Future{println("step 3");println(s"$context$a")}
