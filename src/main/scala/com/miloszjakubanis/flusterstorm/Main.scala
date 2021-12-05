package com.miloszjakubanis.flusterstorm

import com.miloszjakubanis.flusterstorm.job.{AbstractJob, PrintingJob, StorageJob}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object Main extends App {

  implicit val ec = ExecutionContext.global

  val job = new AbstractJob[String, Int](e => Future { Integer.parseInt(e) })
  val job2 = new AbstractJob[Int, Int](e => Future(e * e))
  val job3 = new AbstractJob[Int, Double](e => Future(e / 2.3))
  val job4 = new StorageJob[Double, Double](e => Future(e + e))
  val job5 = new PrintingJob[Double]()

  val aa = job + job2 + job3 + job4// + job5

  aa.apply("11").onComplete {
    case Failure(exception) => exception.printStackTrace()
    case Success(value) => println(s"Found value: $value")
  }


}
