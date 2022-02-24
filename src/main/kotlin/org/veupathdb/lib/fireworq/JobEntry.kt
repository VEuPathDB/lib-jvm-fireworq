package org.veupathdb.lib.fireworq

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import java.time.LocalDateTime

data class JobEntry(
  @JsonProperty("id")          val jobID:      Int,
  @JsonProperty("category")    val category:   String,
  @JsonProperty("url")         val url:        String,
  @JsonProperty("payload")     val payload:    JsonNode,
  @JsonProperty("status")      val status:     String,
  @JsonProperty("created_at")  val createdAt:  LocalDateTime,
  @JsonProperty("next_try")    val nextTry:    LocalDateTime,
  @JsonProperty("timeout")     val timeout:    Long,
  @JsonProperty("fail_count")  val failCount:  Int,
  @JsonProperty("max_retries") val maxRetries: Int,
  @JsonProperty("retry_delay") val retryDelay: Int,
)
