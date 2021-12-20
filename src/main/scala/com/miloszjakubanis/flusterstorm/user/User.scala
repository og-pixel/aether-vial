package com.miloszjakubanis.flusterstorm.user

import scala.concurrent.ExecutionContext
import com.miloszjakubanis.flusterstorm.job.Job
import com.miloszjakubanis.flusterstorm.pipeline.Pipeline
import scala.collection.mutable
import com.miloszjakubanis.flusterstorm.job.JobRecord


//TODO remove runnable?
trait User {

  val userId: Long
  val userName: String

  override def toString: String = s"ID: $userId, Name: $userName"
}
