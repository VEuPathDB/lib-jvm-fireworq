package org.veupathdb.lib.fireworq

import com.fasterxml.jackson.annotation.JsonProperty

data class NodeDetails(
  @JsonProperty("id")   val nodeID: String,
  @JsonProperty("host") val host:   String,
)
