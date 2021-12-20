package com.miloszjakubanis.flusterstorm.user

import com.miloszjakubanis.flusterstorm.pipeline.Pipeline
import com.miloszjakubanis.flusterstorm.job.{Job, JobRecord}
import com.typesafe.scalalogging.StrictLogging

class AbstractUser(
    val userId: Long,
    val userName: String,
) extends User with StrictLogging {


}
