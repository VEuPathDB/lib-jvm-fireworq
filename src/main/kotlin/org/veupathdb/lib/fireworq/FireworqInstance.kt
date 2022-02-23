package org.veupathdb.lib.fireworq

@Suppress("unused")
data class FireworqInstance(val fireworqURL: String) {
  fun getQueues() = Fireworq.getQueues(fireworqURL)

  fun getQueuesStats() = Fireworq.getQueuesStats(fireworqURL)

  fun getQueue(queue: String) = Fireworq.getQueue(fireworqURL, queue)

  fun putQueue(queue: QueueDetails) = Fireworq.putQueue(fireworqURL, queue)

  fun deleteQueue(queue: String) = Fireworq.deleteQueue(fireworqURL, queue)

  fun getQueueNode(queue: String) = Fireworq.getQueueNode(fireworqURL, queue)

  fun getQueueStats(queue: String) = Fireworq.getQueueStats(fireworqURL, queue)

  fun getRoutings() = Fireworq.getRoutings(fireworqURL)

  fun getRouting(category: String) = Fireworq.getRouting(fireworqURL, category)

  fun putRouting(routing: RoutingDetails) = Fireworq.putRouting(fireworqURL, routing)

  fun deleteRouting(category: String) = Fireworq.deleteRouting(fireworqURL, category)

  fun getGrabbedJobs(queue: String, cursor: String? = null) = Fireworq.getGrabbedJobs(fireworqURL, queue, cursor)

  fun getWaitingJobs(queue: String, cursor: String? = null) = Fireworq.getWaitingJobs(fireworqURL, queue, cursor)

  fun getDeferredJobs(queue: String, cursor: String? = null) = Fireworq.getDeferredJobs(fireworqURL, queue, cursor)

  fun getJob(queue: String, jobID: Int) = Fireworq.getJob(fireworqURL, queue, jobID)

  fun deleteJob(queue: String, jobID: Int) = Fireworq.deleteJob(fireworqURL, queue, jobID)

  fun getFailedJobs(queue: String, cursor: String? = null) = Fireworq.getFailedJobs(fireworqURL, queue, cursor)

  fun getFailedJob(queue: String, failID: Int) = Fireworq.getFailedJob(fireworqURL, queue, failID)

  fun deleteFailedJob(queue: String, failID: Int) = Fireworq.deleteFailedJob(fireworqURL, queue, failID)

  fun postJob(category: String, job: JobRequest) = Fireworq.postJob(fireworqURL, category, job)
}
