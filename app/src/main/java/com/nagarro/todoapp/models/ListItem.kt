package com.nagarro.todoapp.models

data class ListItem(
    var userId: Int? = null,
    var id: Int? = null,
    var title: String? = null,
    var completed: Boolean? = null
)