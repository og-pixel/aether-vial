package com.miloszjakubanis.`aether-vial`.pipeline

import com.miloszjakubanis.`aether-vial`.pipeline.PipelineComposition
import com.miloszjakubanis.`aether-vial`.Job
import scala.concurrent.{Future, Await}

trait Pipeline[A, B]:

  val job: Job[A, B]

  def compose[C](pipe: Pipeline[B, C]): Pipeline[A, C] = 
    new PipelineComposition(this, pipe)

  def +[C](pipe: Pipeline[B, C]): Pipeline[A, C] = 
    compose(pipe)

  /**
   * Default Method to run the pipeline
   */
  def apply(a: A): Future[B]