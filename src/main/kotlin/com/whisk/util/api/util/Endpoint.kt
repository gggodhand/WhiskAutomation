package com.whisk.util.api.util

open class Endpoint(val selfUrl: String, private val parent: Endpoint? = null) {
    val url: String
        get() = baseUrl + selfUrl

    val baseUrl: String
        get() {
            var base = (parent?.url ?: "")
            if(base.isNotEmpty() && !base.endsWith("/")) {
                base += "/"
            }
            return base
        }
}
