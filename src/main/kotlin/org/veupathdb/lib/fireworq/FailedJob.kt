package org.veupathdb.lib.fireworq

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import java.time.LocalDateTime

data class FailedJob(
  @JsonProperty("id") val failID: Int,
  @JsonProperty("job_id") val jobID: Int,
  @JsonProperty("category") val category: String,
  @JsonProperty("url") val url: String,
  @JsonProperty("payload") val payload: JsonNode,
  @JsonProperty("result") val result: FailedJobResult,
  @JsonProperty("fail_count") val failCount: Int,
  @JsonProperty("failed_at") val failedAt: LocalDateTime,
  @JsonProperty("created_at") val createdAt: LocalDateTime,
)

data class FailedJobResult(
  @JsonProperty("status") val status: String,
  @JsonProperty("code") val code: Int,
  @JsonProperty("message") val message: String,
)