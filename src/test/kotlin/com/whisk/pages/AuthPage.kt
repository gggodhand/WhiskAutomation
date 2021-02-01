package com.whisk.pages

import com.whisk.util.Settings
import com.whisk.util.selector.Page
import com.whisk.util.selector.Selector
import com.whisk.util.selector.idSelector
import com.whisk.util.selector.textSelector

object AuthPage: Page(idSelector("authentication-form")) {
    val inputEmail = Selector("//input[@name='username']")
    val inputPassword = Selector("//input[@name='password']")
    val btnContinue = textSelector("Continue")

    fun signIn(login: String = Settings.login, password: String = Settings.password) {
        if(inputEmail().exists()) {
            inputEmail.input(login)
            btnContinue.click()
            inputPassword.input(password, true)
        }
    }
}
