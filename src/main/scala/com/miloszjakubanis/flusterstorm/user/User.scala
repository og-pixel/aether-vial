package com.miloszjakubanis.flusterstorm.user

import com.miloszjakubanis.flusterstorm.job.AnyJob

import scala.concurrent.ExecutionContext
import com.miloszjakubanis.flusterstorm.job.Job
import com.miloszjakubanis.flusterstorm.pipeline.Pipeline

object User:
  def apply(factory: () => (name: String) => User): (name: String) => User =
    factory()

  val executionContext = Job.given_ExecutionContext

//TODO remove runnable?
trait User extends Runnable:
  val userId: Long
  val userName: String
  def pipeline: Pipeline[_, _]

  val executionContext: ExecutionContext = User.executionContext

  def run(): Unit = runPipeline()
  
  def submitPipeline(pipeline: Pipeline[_, _]): Unit
  
  def runPipeline(): Unit

  override def toString: String = s"ID: $userId, Name: $userName"