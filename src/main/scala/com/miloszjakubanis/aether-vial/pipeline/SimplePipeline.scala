package com.miloszjakubanis.`aether-vial`.pipeline

import com.miloszjakubanis.`aether-vial`.pipeline.Pipeline
import com.miloszjakubanis.`aether-vial`.Job
import scala.concurrent.Future

/**
 * Simple Pipeline implementation going input->output
 */
class SimplePipeline[A, B](val job: Job[A, B]) extends Pipeline[A, B]:

  def apply(a: A): Future[B] = job(a)