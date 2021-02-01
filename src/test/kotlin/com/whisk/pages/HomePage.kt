package com.whisk.pages

import com.whisk.util.selector.Block
import com.whisk.util.selector.Page
import com.whisk.util.selector.Selector
import com.whisk.util.selector.textSelector

object HomePage: Page() {
    object Menu: Block(Selector("//nav")) {
        val shopping = textSelector("Shopping")
    }
}
