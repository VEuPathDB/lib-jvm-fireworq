package org.veupathdb.lib.fireworq

import com.fasterxml.jackson.annotation.JsonProperty

data class JobsResponse(
  @JsonProperty("jobs")        val jobs: List<JobEntry>,
  @JsonProperty("next_cursor") val nextCursor: String?
)