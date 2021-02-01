package com.whisk.util.api.endpoint.list.entities

import com.whisk.util.api.util.Entity

data class ListResponse(var list: ListItem?=null, var content: HashMap<String, String>?=null): Entity()
