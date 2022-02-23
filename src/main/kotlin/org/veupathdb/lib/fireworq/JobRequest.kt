package org.veupathdb.lib.fireworq

import com.fasterxml.jackson.annotation.JsonProperty

data class JobRequest(
  /**
   * An external destination to fire when the job is grabbed.
   */
  @JsonProperty("url") val host: String,

  /**
   * A payload which will be `POST`ed to `url` on firing the job.
   *
   * It can be any JSON value. If it is a JSON string, then the raw string value
   * not a JSON string will be a request body `POST`ed to `url`.
   */
  @JsonProperty("payload") val payload: Any? = null,

  /**
   * Seconds to wait before grabbing the job.
   */
  @JsonProperty("run_after") val runAfter: Int = 0,

  /**
   * The maximum number of retrying the job when the external destination
   * returned a failure.
   */
  @JsonProperty("max_retries") val maxRetries: Int = 0,

  /**
   * A delay in seconds to wait before grabbing the retrying job.
   */
  @JsonProperty("retry_delay") val retryDelay: Int = 0,

  /**
   * A timeout, in seconds, of the response from the external destination.
   *
   * 0 means no timeout.
   */
  @JsonProperty("timeout") val timeout: Int = 0,
)