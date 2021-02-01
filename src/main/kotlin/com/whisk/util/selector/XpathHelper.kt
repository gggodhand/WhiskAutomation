package com.whisk.util.selector

object XpathHelper {
    fun xp_id(id: String) = "//*[@data-testid='$id']"
    fun xp_text(text: String) = "//*[text()='$text']"
}
