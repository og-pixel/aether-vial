package com.miloszjakubanis.flusterstorm.job

import com.miloszjakubanis.flusterstorm.job.Job.given_ExecutionContext
import com.miloszjakubanis.flusterstorm.job.{AbstractJob, Job}

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future

/** Prints result to the console as soon as possible
  * @tparam A
  *   Any parameter that can be printer
  */
class PrintingJob[A]
    extends AbstractJob[A, Unit](e => Future { println(s"$e") })
