package com.miloszjakubanis.flusterstorm.job

import scala.concurrent.Future

case class JobRecord[V](key: String, value: Future[V])
