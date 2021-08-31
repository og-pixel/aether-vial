package com.miloszjakubanis.flusterstorm.user

import scala.collection.mutable
import com.miloszjakubanis.flusterstorm.job.{AnyJob, Job}

class AbstractUser(val userId: Long, val userName: String) extends User:

  private val queue: mutable.Queue[Job[_, _]] = new mutable.Queue()

  private var numberOfJobs: Int = 1

  //TODO might be not atomic
  //TODO it should propably return something differnt
  def submitJob(job: Job[_, _]): Int =
    queue += job
    queue.length

  def getHeadJob(): Job[_, _] = queue.head