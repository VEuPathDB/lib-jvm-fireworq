package org.veupathdb.lib.fireworq

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class NewJobResponse(
  @JsonProperty("id")         val jobID:      Int,
  @JsonProperty("queue_name") val queueName:  String,
  @JsonProperty("category")   val category:   String,
  @JsonProperty("url")        val url:        String,
  @JsonProperty("payload")    val payload:    JsonNode,
  @JsonProperty("runAfter")   val runAfter:   Int,
  @JsonProperty("maxRetries") val maxRetries: Int,
  @JsonProperty("retryDelay") val retryDelay: Int,
  @JsonProperty("timeout")    val timeout:    Int,
)
