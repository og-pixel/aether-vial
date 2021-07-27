package com.miloszjakubanis.`aether-vial`

trait JobWithInput[A, B](val input: A) extends Job[A, B]