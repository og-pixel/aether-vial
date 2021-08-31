package com.miloszjakubanis.flusterstorm.user

import com.miloszjakubanis.flusterstorm.job.AnyJob
import scala.concurrent.ExecutionContext
import com.miloszjakubanis.flusterstorm.job.Job

object User:
  def apply(factory: () => User): User = factory()
  val executionContext = Job.given_ExecutionContext

trait User extends Runnable:
  val userId: Long
  val userName: String

  val executionContext: ExecutionContext = User.executionContext

  def run(): Unit = println("RUN THIS PROGRAM")