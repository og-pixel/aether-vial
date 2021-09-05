package com.miloszjakubanis.flusterstorm.pipeline

import com.miloszjakubanis.flusterstorm.job.Job

import java.util.concurrent.ConcurrentLinkedQueue
import scala.collection.mutable.ArrayBuffer

class AbstractPipeline[A, B](val job: Job[A, B],
    var inputData: ConcurrentLinkedQueue[A] = new ConcurrentLinkedQueue[A](),
    var outputData: ConcurrentLinkedQueue[B] = new ConcurrentLinkedQueue[B]()
) extends Pipeline[A, B]
