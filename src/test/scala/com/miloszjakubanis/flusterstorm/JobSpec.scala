package com.miloszjakubanis.flusterstorm

import com.miloszjakubanis.flusterstorm.job.{AbstractJob, PrintingJob, StorageJob}
import com.typesafe.scalalogging.StrictLogging
import utest.{TestSuite, Tests, test}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object JobSpec extends TestSuite with StrictLogging {

  implicit val ec = ExecutionContext.global

  override def tests: Tests = Tests {
    test("Job Simple") - {
      val job = new AbstractJob[String, Int](e => Future { Integer.parseInt(e) })

      job("1").onComplete {
        case Failure(_) => assert(false)
        case Success(_) => assert(true)
      }
    }

    test("Job Mixed") - {
      val job = new AbstractJob[String, Int](e => Future { Integer.parseInt(e) })
      val job2 = new AbstractJob[Int, Int](e => Future(e * e))

      val longJob = job + job2
      longJob("1").onComplete {
        case Failure(_) => assert(false)
        case Success(v) => assert(v == 1)
      }
    }
  }
}
