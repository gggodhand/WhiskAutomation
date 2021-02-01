package com.whisk.util.api.endpoint.common

data class ErrorResponse(
    var code: String? = null,
    var error_code: String? = null,
    var message: String? = null
)
