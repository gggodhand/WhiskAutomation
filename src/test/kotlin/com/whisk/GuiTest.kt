package com.whisk

import com.codeborne.selenide.Selenide.open
import com.whisk.pages.AuthPage
import com.whisk.pages.HomePage
import com.whisk.pages.ShoppingTab
import com.whisk.util.selector.Selector
import com.whisk.util.selector.TestCaseDsl.step
import io.qameta.allure.Epic
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@Epic("UI Tests")
class GuiTest: BaseTest() {

    @Test
    fun test1() {
        step("1. Navigate to https://my.whisk-dev.com/") {
            open("https://my.whisk-dev.com/")
        }
        step("2. Sign in") {
            AuthPage.signIn()
        }
        step("3. Navigate to the Shopping tab") {
            HomePage.Menu.shopping.click()
        }
        step("4. Create a Shopping list") {
            ShoppingTab.create()
        }
        step("5. Add 5 items") {
            repeat(5) {
                ShoppingTab.ListDetails.addItem()
                items.add(Selector.prevInputString)
            }
        }

        assertEquals(5, items.size,"Items list should have 5 elements")
    }

    @Test(dataProvider = "itemTests")
    fun checkItem(item: String) {
        val items = ShoppingTab.ListDetails.items.textValues
        assertTrue(items.find { it.equals(item, ignoreCase = true) } != null,
            "Item '$item' doesn't exist")
    }

    @Test
    fun test2() {
        step("1. Navigate to https://my.whisk-dev.com/") {
            open("https://my.whisk-dev.com/")
        }
        step("2. Sign in") {
            AuthPage.signIn()
        }
        step("3. Navigate to the Shopping tab") {
            HomePage.Menu.shopping.click()
        }
        step("4. Create a Shopping list") {
            ShoppingTab.create()
        }
        step("5. Delete Shopping list") {
            ShoppingTab.ListDetails.delete()
        }

        assertTrue(ShoppingTab.Lists.item.textValues.find { it == Selector.prevInputString } == null,
        "Shopping list was not deleted")
    }

    @DataProvider
    fun itemTests() = items.toTypedArray()

    private val items = ArrayList<String>()
}
