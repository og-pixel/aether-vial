package com.miloszjakubanis.flusterstorm.job

import com.miloszjakubanis.flusterstorm.job.Job.given_ExecutionContext
import com.miloszjakubanis.flusterstorm.job.{AbstractJob, Job}

import scala.concurrent.Future

/** Prints result to the console as soon as possible
  * @tparam A
  *   Any parameter that can be printer
  */
class PrintingJob[A]//(context: A = "")
    extends AbstractJob[A, Unit](e => Future { println(s"$e") })

//  override def apply(a: A): Future[Unit] = Future(println(context))
