package com.whisk.util.api.endpoint

import com.whisk.util.api.util.Endpoint
import com.whisk.util.api.endpoint.list.List
import io.qameta.allure.Allure

object API: Endpoint("https://api.whisk-dev.com") {
    val list = List(this)
}

fun<T> step(msg: String, lambda: ()->T): T {
    return Allure.step(msg, Allure.ThrowableRunnable {
        val res = lambda()
        res
    })
}
