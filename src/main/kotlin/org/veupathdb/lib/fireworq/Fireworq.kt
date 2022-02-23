package org.veupathdb.lib.fireworq

import org.veupathdb.lib.jackson.Json
import java.io.InputStream
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Suppress("unused", "NOTHING_TO_INLINE")
object Fireworq {

  @JvmStatic
  private inline fun queuesURL(u: String) = "$u/queues"

  @JvmStatic
  private inline fun queueStatsURL(u: String) = "$u/queues/stats"

  @JvmStatic
  private inline fun queueURL(u: String, q: String) = "$u/queue/$q"

  @JvmStatic
  private inline fun queueNodeURL(u: String, q: String) = "$u/queue/$q/node"

  @JvmStatic
  private inline fun queueStatsURL(u: String, q: String) = "$u/queue/$q/stats"

  @JvmStatic
  private inline fun routingURL(u: String) = "$u/routings"

  @JvmStatic
  private inline fun routingURL(u: String, c: String) = "$u/routing/$c"

  @JvmStatic
  private inline fun grabbedURL(u: String, q: String, c: String?) =
    if (c == null)
      "$u/queue/$q/grabbed"
    else
      "$u/queue/$q/grabbed?cursor=$c"

  @JvmStatic
  private inline fun waitingURL(u: String, q: String, c: String?) =
    if (c == null)
      "$u/queue/$q/waiting"
    else
      "$u/queue/$q/waiting?cursor=$c"

  @JvmStatic
  private inline fun deferredURL(u: String, q: String, c: String?) =
    if (c == null)
      "$u/queue/$q/deferred"
    else
      "$u/queue/$q/deferred?cursor=$c"

  @JvmStatic
  private inline fun jobURL(u: String, q: String, j: Int) = "$u/queue/$q/job/$j"

  @JvmStatic
  private inline fun failedURL(u: String, q: String, c: String?) =
    if (c == null)
      "$u/queue/$q/failed"
    else
      "$u/queue/$q/failed?cursor=$c"

  @JvmStatic
  private inline fun failedURL(u: String, q: String, f: Int) =
    "$u/queue/$q/failed/$f"

  @JvmStatic
  private inline fun categoryURL(u: String, c: String) = "$u/job/$c"


  /**
   * Gets a list of all the queues currently configured on the Fireworq server
   * at the given URL.
   *
   * @param url URL to the Fireworq server to connect to.
   *
   * @return The list of queues configured on the target Fireworq server.
   */
  @JvmStatic
  fun getQueues(url: String): List<QueueDetails> =
    getString(queuesURL(url)) { Json.parse(it.body()) }

  /**
   * Gets the stats for all the queues currently configured on the Fireworq
   * server at the given URL.
   *
   * @param url URL to the Fireworq server to connect to.
   *
   * @return A map of queue names to stats for that queue.
   */
  @JvmStatic
  fun getQueuesStats(url: String): Map<String, QueueStats> =
    getString(queueStatsURL(url)) { Json.parse(it.body()) }

  /**
   * Gets the details for the queue defined by the given queue name on the
   * Fireworq server at the given URL.
   *
   * @param url URL to the Fireworq server to connect to.
   *
   * @param queue Name of the queue to fetch details for.
   *
   * @return Details for the target queue.
   *
   * @throws QueueResponseException If the Fireworq server responds with
   * anything other than a `200` status code.
   */
  @JvmStatic
  fun getQueue(url: String, queue: String): QueueDetails =
    getString(queueURL(url, queue)) { Json.parse(it.body()) }

  /**
   * Creates or overwrites a queue on the Fireworq server at the given URL.
   *
   * @param url URL to the Fireworq server to connect to.
   *
   * @param queue Definition of the queue to create.
   *
   * @return Details for the created queue. (Should be the same as the input).
   *
   * @throws QueueResponseException If the Fireworq server responds with
   * anything other than a `200` status code.
   */
  @JvmStatic
  fun putQueue(url: String, queue: QueueDetails): QueueDetails =
    putString(queueURL(url, queue.name), queue) { Json.parse(it.body()) }

  @JvmStatic
  fun deleteQueue(url: String, queue: String): QueueDetails =
    deleteString(queueURL(url, queue)) { Json.parse(it.body()) }

  @JvmStatic
  fun getQueueNode(url: String, queue: String): NodeDetails =
    getString(queueNodeURL(url, queue)) { Json.parse(it.body()) }

  @JvmStatic
  fun getQueueStats(url: String, queue: String): QueueStats =
    getString(queueStatsURL(url, queue)) { Json.parse(it.body()) }

  @JvmStatic
  fun getRoutings(url: String): List<RoutingDetails> =
    getStream(routingURL(url)) { Json.parse(it.body()) }

  @JvmStatic
  fun getRouting(url: String, category: String): RoutingDetails =
    getString(routingURL(url, category)) { Json.parse(it.body()) }

  @JvmStatic
  fun putRouting(url: String, routing: RoutingDetails): RoutingDetails =
    putString(routingURL(url, routing.jobCategory), routing) {
      Json.parse(it.body())
    }

  @JvmStatic
  fun deleteRouting(url: String, category: String): RoutingDetails =
    deleteString(routingURL(url, category)) { Json.parse(it.body()) }

  @JvmStatic
  fun getGrabbedJobs(
    url: String,
    queue: String,
    cursor: String? = null,
  ): JobsResponse =
    getStream(grabbedURL(url, queue, cursor)) { Json.parse(it.body()) }

  @JvmStatic
  fun getWaitingJobs(
    url: String,
    queue: String,
    cursor: String? = null,
  ): JobsResponse =
    getStream(waitingURL(url, queue, cursor)) { Json.parse(it.body()) }

  @JvmStatic
  fun getDeferredJobs(
    url: String,
    queue: String,
    cursor: String? = null,
  ): JobsResponse =
    getStream(deferredURL(url, queue, cursor)) { Json.parse(it.body()) }

  @JvmStatic
  fun getJob(url: String, queue: String, jobID: Int): JobEntry =
    getString(jobURL(url, queue, jobID)) { Json.parse(it.body()) }

  @JvmStatic
  fun deleteJob(url: String, queue: String, jobID: Int): JobEntry =
    deleteString(jobURL(url, queue, jobID)) { Json.parse(it.body()) }

  @JvmStatic
  fun getFailedJobs(
    url: String,
    queue: String,
    cursor: String? = null,
  ): FailedJobsResponse =
    getStream(failedURL(url, queue, cursor)) { Json.parse(it.body()) }

  @JvmStatic
  fun getFailedJob(url: String, queue: String, failID: Int): FailedJob =
    getString(failedURL(url, queue, failID)) { Json.parse(it.body()) }

  @JvmStatic
  fun deleteFailedJob(url: String, queue: String, failID: Int): FailedJob =
    deleteString(failedURL(url, queue, failID)) { Json.parse(it.body()) }

  @JvmStatic
  fun postJob(
    url: String,
    category: String,
    request: JobRequest,
  ): NewJobResponse {
    val res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder(URI.create(categoryURL(url, category)))
        .POST(HttpRequest.BodyPublishers.ofString(Json.from(request).toString()))
        .build(),
      HttpResponse.BodyHandlers.ofString()
    )

    if (res.statusCode() != 200)
      throw QueueResponseException(res.statusCode())

    return Json.parse(res.body())
  }

  @JvmStatic
  private inline fun <T> deleteString(
    url: String,
    fn: (HttpResponse<String>) -> T,
  ): T {
    val res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder(URI.create(url)).DELETE().build(),
      HttpResponse.BodyHandlers.ofString()
    )

    if (res.statusCode() != 200)
      throw QueueResponseException(res.statusCode())

    return fn(res)
  }

  @JvmStatic
  private inline fun <T> putString(
    url: String,
    bod: Any,
    fn: (HttpResponse<String>) -> T,
  ): T {
    val res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder(URI.create(url))
        .PUT(HttpRequest.BodyPublishers.ofString(Json.from(bod).toString()))
        .build(),
      HttpResponse.BodyHandlers.ofString()
    )

    if (res.statusCode() != 200)
      throw QueueResponseException(res.statusCode())

    return fn(res)
  }

  @JvmStatic
  private inline fun <T> getStream(
    u: String,
    fn: (HttpResponse<InputStream>) -> T,
  ): T {
    val res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder(URI.create(u)).GET().build(),
      HttpResponse.BodyHandlers.ofInputStream()
    )

    if (res.statusCode() != 200)
      throw QueueResponseException(res.statusCode())

    return fn(res).also { res.body().close() }
  }

  @JvmStatic
  private inline fun <T> getString(
    u: String,
    fn: (HttpResponse<String>) -> T,
  ): T {
    val res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder(URI.create(u)).GET().build(),
      HttpResponse.BodyHandlers.ofString()
    )

    if (res.statusCode() != 200)
      throw QueueResponseException(res.statusCode())

    return fn(res)
  }
}