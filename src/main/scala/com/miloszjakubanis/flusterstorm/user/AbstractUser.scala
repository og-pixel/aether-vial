package com.miloszjakubanis.flusterstorm.user

import scala.collection.mutable
import com.miloszjakubanis.flusterstorm.job.{AbstractJob, AnyJob, Job}
import com.miloszjakubanis.flusterstorm.pipeline.Pipeline
import com.miloszjakubanis.flusterstorm.job.Job.given_ExecutionContext

import scala.concurrent.Future

class AbstractUser(
    val userId: Long,
    val userName: String,
    private[this] var _pipeline: Pipeline[_, _] | Null = null
) extends User:

//  private val queue: mutable.Queue[Job[_, _]] = new mutable.Queue()

  private var numberOfJobs: Int = 1

  override def pipeline: Pipeline[_, _] =
    if _pipeline == null then throw new RuntimeException("Pipeline not defined")
    else _pipeline.nn

  //TODO might be not atomic
  //TODO it should propably return something differnt
//  def submitJob(job: Job[_, _]): Int =
//    queue += job
//    queue.length

//  def getHeadJob(): Job[_, _] = queue.head

  def submitPipeline(pipeline: Pipeline[_, _]): Unit =
    _pipeline = pipeline
    
  def runPipeline(): Unit = pipeline.run()
