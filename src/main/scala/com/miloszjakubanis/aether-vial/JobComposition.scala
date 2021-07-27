package com.miloszjakubanis.`aether-vial`

import scala.concurrent.Future
import java.time.Duration

class JobComposition[A, B, C](input: A)(fun: A => Future[B])(
    fun2: B => Future[C]
) extends AbstractJob[A, B](input)(fun):

  def this(input: A)(job: Job[A, B])(job2: Job[B, C]) =
    this(input)(job.fun)(job2.fun)
