package com.nagarro.todoapp.data.network

import com.nagarro.todoapp.models.ListItem
import retrofit2.Response
import retrofit2.http.GET

interface ToDoListApi{
    @GET("/todos")
    suspend fun getTodoListData(): Response<List<ListItem>>
}