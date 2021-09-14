package com.miloszjakubanis.flusterstorm.job

import scala.concurrent.Future
import java.util.concurrent.ConcurrentLinkedQueue

class StorageJob[A, B](fun: A => Future[B])
    extends Job[A, B] with Function1[A, Future[B]]:

  val storage: ConcurrentLinkedQueue[Future[B]] = new ConcurrentLinkedQueue()

  override def apply(a: A): Future[B] =
    val future = fun(a)
    storage.add(future)
    future