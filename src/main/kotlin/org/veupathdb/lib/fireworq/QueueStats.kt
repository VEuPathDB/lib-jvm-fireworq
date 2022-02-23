package org.veupathdb.lib.fireworq

import com.fasterxml.jackson.annotation.JsonProperty

data class QueueStats(
  @JsonProperty("total_pushes")             val totalPushes:            Int,
  @JsonProperty("total_pops")               val totalPops:              Int,
  @JsonProperty("total_successes")          val totalSuccesses:         Int,
  @JsonProperty("total_failures")           val totalFailures:          Int,
  @JsonProperty("total_permanent_failures") val totalPermanentFailures: Int,
  @JsonProperty("total_completes")          val totalCompletes:         Int,
  @JsonProperty("total_elapsed")            val totalElapsed:           Int,
  @JsonProperty("pushes_per_second")        val pushesPerSecond:        Int,
  @JsonProperty("pops_per_second")          val popsPerSecond:          Int,
  @JsonProperty("outstanding_jobs")         val outstandingJobs:        Int,
  @JsonProperty("total_workers")            val totalWorkers:           Int,
  @JsonProperty("idle_workers")             val idleWorkers:            Int,
  @JsonProperty("active_nodes")             val activeNodes:            Int,
)