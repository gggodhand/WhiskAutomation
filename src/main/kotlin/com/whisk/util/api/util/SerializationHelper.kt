package com.whisk.util.api.util

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import io.qameta.allure.Allure
import io.restassured.response.Response

object SerializationHelper {
    private val gson = Gson()

    fun<T> deserialize(response: Response, cls: Class<T>): T? {
        val body = response.body.asString()
        return if(body.isEmpty()) {
            null
        } else {
            try {
                gson.fromJson(body, cls)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
                throw e
            }
        }
    }

    fun Any?.toJson() = gson.toJson(this)

}

