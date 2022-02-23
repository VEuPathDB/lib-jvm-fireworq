package org.veupathdb.lib.fireworq

data class QueueResponseException(val code: Int) : RuntimeException()