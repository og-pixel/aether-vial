package com.miloszjakubanis.flusterstorm.job

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/** Prints result to the console as soon as possible
  * @tparam A
  *   Any parameter that can be printer
  */
class PrintingJob[A]
    extends AbstractJob[A, Unit](e => Future { println(e) })
