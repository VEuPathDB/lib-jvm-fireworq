package org.veupathdb.lib.fireworq

import com.fasterxml.jackson.annotation.JsonProperty

data class RoutingDetails(
  @JsonProperty("job_category") val jobCategory: String,
  @JsonProperty("queue_name")   val queueName:   String,
)
