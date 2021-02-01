package com.whisk.util.selector

import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Allure
import org.openqa.selenium.By
import com.whisk.util.selector.TestCaseDsl.step
import com.whisk.util.selector.XpathHelper.xp_id
import com.whisk.util.selector.XpathHelper.xp_text
import org.openqa.selenium.Keys

open class Selector(var xpath: String) {
    var base: Selector? = null
    var name: String = ""

    fun toXpath(): String {
        var res = ""
        if(base != null) {
            res = base!!.toXpath()
        }

        return "$res$xpath"
    }

    operator fun invoke(): SelenideElement {
        return `$`(By.xpath(toXpath()))
    }

    fun click() {
        step("Clicking at $name") {
            this().click()
            addXpathAttach()
        }
    }

    fun input(text: String, pressEnter: Boolean = false, needClear: Boolean = false) {
        step("Input '$text' to the $name") {
            val el = this()

            if(needClear) {
                el.toWebElement().click()
                while(!el.value.isNullOrEmpty()) {
                    el.sendKeys(Keys.BACK_SPACE)
                }
            }

            el.sendKeys(text)
            prevInputString = text

            if(pressEnter) {
                el.pressEnter()
            }

            addXpathAttach()
        }
    }

    val textValues: Array<String>
        get() {
            return step("Getting text values from $name") {
                addXpathAttach()
                val res = `$$`(By.xpath(toXpath())).texts().toTypedArray()
                Allure.addAttachment("result", res.joinToString())
                res
            }
        }


    private fun addXpathAttach() = Allure.addAttachment("xpath", this.toXpath())

    companion object {
        var prevInputString = ""
    }
}

fun idSelector(id: String) = Selector(xp_id(id))
fun textSelector(text: String) = Selector(xp_text(text))
