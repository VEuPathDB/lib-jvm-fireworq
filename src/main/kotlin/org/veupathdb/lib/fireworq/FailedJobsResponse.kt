package org.veupathdb.lib.fireworq

import com.fasterxml.jackson.annotation.JsonProperty

data class FailedJobsResponse(
  @JsonProperty("failed_jobs") val jobs: List<FailedJob>,
  @JsonProperty("next_cursor") val nextCursor: String? = null,
)