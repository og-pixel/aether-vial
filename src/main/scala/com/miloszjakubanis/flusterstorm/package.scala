package com.miloszjakubanis

import java.time.Duration

package object flusterstorm {

  def measureTime[A](fun: => Unit, limit: Duration): Unit = {

    var elapsed: Long = 0
    do {
      val start = System.currentTimeMillis
      fun
      elapsed = elapsed + (System.currentTimeMillis - start)
    } while (limit.toMillis > elapsed)

  }

}
