package com.miloszjakubanis.flusterstorm.user

import scala.collection.mutable
import com.miloszjakubanis.flusterstorm.job.{AbstractJob, AnyJob, Job}
import com.miloszjakubanis.flusterstorm.pipeline.Pipeline
import com.miloszjakubanis.flusterstorm.job.Job.given_ExecutionContext
import scala.concurrent.Future

class AbstractUser(
    val userId: Long,
    val userName: String,
    _pipeline: Option[Pipeline[_, _]] = Option.empty
) extends User:

  def submitPipeline(key: String, pipeline: Pipeline[_, _]): Unit =
    if pipelineList.exists(e => e._1 == key && e._2 == pipeline)
    then
      println("already exists")
      return
    else pipelineList.put(key, pipeline)