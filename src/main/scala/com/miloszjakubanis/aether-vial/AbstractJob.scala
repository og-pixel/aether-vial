package com.miloszjakubanis.`aether-vial`

import java.util.concurrent.{ExecutorService, Executors}
import scala.concurrent.{ExecutionContext, Future}


open class AbstractJob[A, B]
  (val input: A)
  (val fun: A => Future[B])
 extends Job[A, B]:
  def apply(): Future[B] = fun(input)


