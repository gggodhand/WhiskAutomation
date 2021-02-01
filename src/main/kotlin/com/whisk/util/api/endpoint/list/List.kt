package com.whisk.util.api.endpoint.list

import com.whisk.util.api.endpoint.list.entities.ListItem
import com.whisk.util.api.endpoint.list.entities.ListResponse
import com.whisk.util.api.util.ApiHelper
import com.whisk.util.api.util.Endpoint
import com.whisk.util.api.util.Entity

class List(parent: Endpoint): Endpoint("list/v2", parent ) {
    fun post(request: ListItem = ListItem().default()) = ApiHelper.post<ListResponse>(url, request)
    fun get(id: String) = ApiHelper.get<ListResponse>("$url/$id")
    fun delete(id: String) = ApiHelper.delete<Entity>("$url/$id")
}
