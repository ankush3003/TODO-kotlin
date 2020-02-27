package com.nagarro.todoapp.models

data class ListItem(
    val userId: Int? = null,
    val id: Int? = null,
    var title: String? = null,
    var completed: Boolean? = null
)
//Q: Inline, scope and extension fuctions