package com.miloszjakubanis.`aether-vial`

import com.miloszjakubanis.`aether-vial`.AbstractJob
import com.miloszjakubanis.`aether-vial`.given_ExecutionContext
import scala.util.{Failure, Success}
import scala.concurrent.Future

class PrintingJob[A](input: A) extends AbstractJob[A, Unit](input)(e => Future(println(e)))