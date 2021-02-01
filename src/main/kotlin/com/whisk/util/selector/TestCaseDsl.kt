package com.whisk.util.selector

import io.qameta.allure.Allure
import io.qameta.allure.Attachment
import org.openqa.selenium.OutputType
import com.codeborne.selenide.Selenide
import java.util.*


object TestCaseDsl {
    fun<T> step(msg: String, lambda: ()->T): T {
        return Allure.step(msg, Allure.ThrowableRunnable {
            val res = lambda()
            takeScreenshot()
            res
        })
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    fun takeScreenshot(): ByteArray {
        val screenshotAsBase64 = Selenide.screenshot(OutputType.BASE64)
        return Base64.getDecoder().decode(screenshotAsBase64)
    }
}
