package com.miloszjakubanis.flusterstorm.pipeline

import com.miloszjakubanis.flusterstorm.job.Job

class AbstractPipeline[A, B](val job: Job[A, B]) extends Pipeline[A, B]
