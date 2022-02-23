package org.veupathdb.lib.fireworq

import com.fasterxml.jackson.annotation.JsonProperty

data class QueueDetails @JvmOverloads constructor(
  /**
   * The name of the target queue.
   */
  @JsonProperty("name") val name: String,

  /**
   * An interval, in milliseconds, at which Fireworq checks the arrival of new
   * jobs in this queue.
   */
  @JsonProperty("polling_interval") val pollingInterval: Int? = null,

  /**
   * The maximum number of jobs that are processed simultaneously for this
   * queue.
   */
  @JsonProperty("max_workers") val maxWorkers: Int? = null,

  /**
   * The maximum floating-point number of dispatches allowed to be processed
   * within a second for this queue.
   */
  @JsonProperty("max_dispatches_per_second") val maxDispatchesPerSecond: Float? = null,

  /**
   * The maximum number of burst size of throttling configuration for this
   * queue.
   */
  @JsonProperty("max_burst_size") val maxBurstSize: Int? = null
)
