package com.miloszjakubanis.`aether-vial`

import com.miloszjakubanis.`aether-vial`.AbstractJob
import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext

class PrinterJob[A](input: A)
    extends AbstractJob[A, Unit](input)(e => Future{println(e)})
