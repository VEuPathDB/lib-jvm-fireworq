package org.veupathdb.lib.fireworq

@Suppress("unused")
data class FireworqQueue(
  val instance:  FireworqInstance,
  val queueName: String,
) {

  constructor(fireworqURL: String, queueName: String):
    this(FireworqInstance(fireworqURL), queueName)

  fun getDetails(): QueueDetails =
    instance.getQueue(queueName)

  fun getNode(): NodeDetails =
    instance.getQueueNode(queueName)

  fun getStats(): QueueStats =
    instance.getQueueStats(queueName)

  fun getGrabbed(): List<JobEntry> {
    val out = ArrayList<JobEntry>(100)
    var cur = null as String?

    while (true) {
      val res = instance.getGrabbedJobs(queueName, cur)
      cur = res.nextCursor
      out.addAll(res.jobs)

      if (cur.isNullOrBlank())
        break
    }

    return out
  }

  fun getWaiting(): List<JobEntry> {
    val out = ArrayList<JobEntry>(100)
    var cur = null as String?

    while (true) {
      val res = instance.getWaitingJobs(queueName, cur)
      cur = res.nextCursor
      out.addAll(res.jobs)

      if (cur.isNullOrBlank())
        break
    }

    return out
  }

  fun getDeferred(): List<JobEntry> {
    val out = ArrayList<JobEntry>(100)
    var cur = null as String?

    while (true) {
      val res = instance.getDeferredJobs(queueName, cur)
      cur = res.nextCursor
      out.addAll(res.jobs)

      if (cur.isNullOrBlank())
        break
    }

    return out
  }

  fun getFailed(): List<FailedJob> {
    val out = ArrayList<FailedJob>(100)
    var cur = null as String?

    while (true) {
      val res = instance.getFailedJobs(queueName, cur)
      cur = res.nextCursor
      out.addAll(res.jobs)

      if (cur.isNullOrBlank())
        break
    }

    return out
  }

  fun getFailed(failID: Int): FailedJob? {
    return try {
      instance.getFailedJob(queueName, failID)
    } catch (e: QueueResponseException) {
      if (e.code == 404)
        null
      else
        throw e
    }
  }

  fun getJob(jobID: Int): JobEntry? {
    return try {
      instance.getJob(queueName, jobID)
    } catch (e: QueueResponseException) {
      if (e.code == 404)
        null
      else
        throw e
    }
  }

  fun jobInFailList(jobID: Int): Boolean {
    val list = getFailed()

    when (list.size) {
      0 -> return false
      1 -> return list[0].jobID == jobID
      2 -> return list[0].jobID == jobID || list[1].jobID == jobID
    }

    return list.binarySearch {
      if (it.jobID < jobID)
        -1
      else if (it.jobID > jobID)
        1
      else
        0
    } > -1
  }

  /**
   * Deletes the failed job entry with the given failure ID.
   *
   * If no such entry exists, this method does nothing.
   *
   * @throws IllegalStateException if the Fireworq server response with anything
   * other than a `200` or `404` status code.
   */
  fun deleteJobFailure(failID: Int) =
    instance.deleteFailedJob(queueName, failID)


  fun submitJob(category: String, request: JobRequest): NewJobResponse =
    instance.postJob(category, request)
}
