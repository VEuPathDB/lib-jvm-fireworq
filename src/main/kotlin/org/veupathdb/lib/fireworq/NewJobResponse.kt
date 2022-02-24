package org.veupathdb.lib.fireworq

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class NewJobResponse(
  @JsonProperty("id")         val jobID:      Int,
  @JsonProperty("queue_name") val queueName:  String,
  @JsonProperty("category")   val category:   String,
  @JsonProperty("url")        val url:        String,
  @JsonProperty("payload")    val payload:    JsonNode,
  @JsonProperty("run_after")   val runAfter:   Int,
  @JsonProperty("max_retries") val maxRetries: Int,
  @JsonProperty("retry_delay") val retryDelay: Int,
  @JsonProperty("timeout")    val timeout:    Int,
)
