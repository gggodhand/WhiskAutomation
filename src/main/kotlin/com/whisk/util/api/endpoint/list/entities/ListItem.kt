package com.whisk.util.api.endpoint.list.entities

import com.whisk.util.api.util.Entity

data class ListItem(var id: String?=null, var name: String?=null, var primary: Boolean? = false): Entity() {

    override fun default(): ListItem {
        name = System.currentTimeMillis().toString()
        primary = false
        return this
    }

}
