package com.whisk.util

import com.whisk.util.properties.Properties
import com.whisk.util.properties.Property

@Properties("settings.properties")
object Settings {
    @Property(key = "user.login")
    var login: String = ""

    @Property(key = "user.password")
    var password: String = ""

    @Property(key = "user.token")
    var token: String = ""
}
