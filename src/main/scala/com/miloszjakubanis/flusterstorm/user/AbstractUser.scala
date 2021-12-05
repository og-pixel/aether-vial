package com.miloszjakubanis.flusterstorm.user

import com.miloszjakubanis.flusterstorm.pipeline.Pipeline

class AbstractUser(
    val userId: Long,
    val userName: String,
    _pipeline: Option[Pipeline[_, _]] = Option.empty
) extends User {


  def submitPipeline(key: String, pipeline: Pipeline[_, _]): Unit =
    if (pipelineList.exists(e => e._1 == key && e._2 == pipeline)) {
      println("already exists")
      return
    }
    else pipelineList.put(key, pipeline)

}
