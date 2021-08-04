package com.miloszjakubanis.`aether-vial`.pipeline

import com.miloszjakubanis.`aether-vial`.{Job, AbstractJob}
import scala.concurrent.{ExecutionContext, Future}

import com.miloszjakubanis.`aether-vial`.given_ExecutionContext

class PipelineComposition[A, B, C](
    val left: Pipeline[A, B],
    val right: Pipeline[B, C]
) extends Pipeline[A, C]:

  override val job: Job[A, C] = left.job + right.job

  override def apply(a: A): Future[C] = job(a)