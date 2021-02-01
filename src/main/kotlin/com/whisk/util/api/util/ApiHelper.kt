package com.whisk.util.api.util

import com.whisk.util.Settings
import com.whisk.util.api.util.SerializationHelper.toJson
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.http.Header
import io.restassured.http.Headers
import io.restassured.response.Response
import io.qameta.allure.restassured.AllureRestAssured

object ApiHelper {
    fun getHeaders(token: String = Settings.token): Headers {
        return Headers(Header("Authorization", token))
    }

    inline fun <reified T: Any> post(url: String, request: Any)
        = ResponseWrapper<T>(T::class.java.newInstance(), post(url, request.toJson()))

    inline fun <reified T: Any> get(url: String)
        = ResponseWrapper<T>(T::class.java.newInstance(), get(url, getHeaders()))

    inline fun <reified T: Any> delete(url: String)
        = ResponseWrapper<T>(T::class.java.newInstance(), delete(url, getHeaders()))

    fun post(url: String,
             jsonBody: String,
             headers: Headers = getHeaders(),
             contentType: ContentType = ContentType.JSON
    ): Response {
        var req = RestAssured.given()
            .baseUri(url)
            .headers(headers)
            .relaxedHTTPSValidation()
            .contentType(contentType)
            .filter(AllureRestAssured())

        if(jsonBody.isNotEmpty()) {
            req = req.body(jsonBody)
        }

        val res = req.post()
        println("POST: $url")
        println("Res: ${res.asString()}")
        return res
    }

    fun get(url: String,
             headers: Headers = getHeaders(),
             contentType: ContentType = ContentType.JSON
    ): Response {
        var req = RestAssured.given()
            .headers(headers)
            .relaxedHTTPSValidation()
            .contentType(contentType)
            .filter(AllureRestAssured())

        return req[url]
    }

    fun delete(url: String,
            headers: Headers = getHeaders(),
            contentType: ContentType = ContentType.JSON
    ): Response {
        var req = RestAssured.given()
            .headers(headers)
            .relaxedHTTPSValidation()
            .contentType(contentType)
            .filter(AllureRestAssured())

        return req.delete(url)
    }
}
