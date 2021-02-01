package com.whisk.util.api.util

import com.whisk.util.api.endpoint.common.ErrorResponse
import io.restassured.response.Response

open class ResponseWrapper<out T: Any>(
    private val respObj: Any,
    var response: Response) {

    operator fun invoke(): T {
        val cls = respObj::class.java
        return (SerializationHelper.deserialize(response, cls) as T?)!!
    }

    fun getError(): ErrorResponse {
        return SerializationHelper.deserialize(response, ErrorResponse::class.java)!!
    }
}

