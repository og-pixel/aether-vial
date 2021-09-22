package com.miloszjakubanis.flusterstorm.user

import com.miloszjakubanis.flusterstorm.job.AnyJob

import scala.concurrent.ExecutionContext
import com.miloszjakubanis.flusterstorm.job.Job
import com.miloszjakubanis.flusterstorm.pipeline.Pipeline

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object User:
  def apply(factory: () => (name: String) => User): (name: String) => User =
    factory()

  val executionContext = Job.given_ExecutionContext

//TODO remove runnable?
trait User extends Runnable:
  val userId: Long
  val userName: String
  val pipelineList: mutable.HashMap[String, Pipeline[_, _]] =
    new mutable.HashMap()

  def pipelineKeys: Array[String] = pipelineList.keys.toArray

  val executionContext: ExecutionContext = User.executionContext

  def run(): Unit = runPipeline()

  def submitPipeline(key: String, pipeline: Pipeline[_, _]): Unit

  def runPipeline(): Unit = pipelineList.head._2.run()

  override def toString: String = s"ID: $userId, Name: $userName"
