package com.whisk.pages

import com.github.javafaker.Faker
import com.whisk.util.selector.*

object ShoppingTab: Page() {
    val createNew = textSelector("Create new list")

    fun create(name: String = System.currentTimeMillis().toString()): String {
        createNew.click()
        CreateListForm.input.input(name, needClear = true)
        CreateListForm.create.click()
        return name
    }

    object Lists: Block() {
        val item = idSelector("shopping-lists-list-name")
    }

    object CreateListForm: Block(Selector("//form")) {
        val input = idSelector("UI_KIT_INPUT")
        val create = idSelector("create-new-shopping-list-create-button")
    }

    object ListDetails: Block() {
        val dotMenu = idSelector("vertical-dots-shopping-list-button")
        val input = idSelector("desktop-add-item-autocomplete")
        val items = idSelector("shopping-list-item-name")

        object ContextMenu: Block() {
            val delete = textSelector("Delete list")
        }

        fun addItem(name: String = Faker().food().ingredient()) {
            input.input(name, true)
        }

        fun delete() {
            dotMenu.click()
            ContextMenu.delete.click()
            textSelector("Confirm delete").click()
        }
    }
}
