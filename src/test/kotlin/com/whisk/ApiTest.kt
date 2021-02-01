package com.whisk

import com.whisk.util.api.endpoint.API
import com.whisk.util.api.endpoint.list.entities.ListResponse
import com.whisk.util.api.endpoint.step
import io.qameta.allure.Epic
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import org.testng.asserts.SoftAssert
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Epic("API Tests")
class ApiTest: BaseTest() {
    lateinit var newList: ListResponse

    @BeforeMethod
    fun procondition() {
        newList = step("1. Create Shopping list POST: /list/v2") {
            API.list.post()()
        }
    }

    @Test
    fun test1() {
        val checkList = step("2. Get Shopping List by id: GET /list/v2/{id}") {
            API.list.get(newList.list?.id!!)()
        }

        step("3. Check Response") {
            assertEquals(newList.list?.id, checkList.list?.id,
                "Response should contains necessary id")
            assertTrue(checkList.content?.isEmpty() == true,
                "Shopping list should be empty")
        }
    }

    @Test
    fun test2() {
        step("2. Delete Shopping list by id DELETE: /list/v2/{id}") {
            API.list.delete(newList.list?.id!!)()
        }
        val checkList = step("3. Get Shopping List by id: GET /list/v2/{id}") {
            API.list.get(newList.list?.id!!)
        }

        step("4. Check Response") {
            val sa = SoftAssert()
            sa.assertEquals(checkList.response.statusCode, 200,
                "Response should be 200")
            sa.assertEquals(checkList.getError().code, "shoppingList.notFound",
                "Incorrect response message")
            sa.assertAll()
        }
    }
}
