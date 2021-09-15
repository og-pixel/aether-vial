package com.miloszjakubanis.flusterstorm.job

import scala.concurrent.Future
import java.util.concurrent.ConcurrentLinkedQueue
import scala.collection.mutable.ArrayBuffer

class StorageJob[A, B](fun: A => Future[B])
    extends Job[A, B]:// with Function1[A, Future[B]]:

  val storage: ArrayBuffer[Future[B]] = new ArrayBuffer()

  override def apply(a: A): Future[B] =
    val future = fun(a)
    storage.addOne(future)
    future