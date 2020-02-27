package com.nagarro.todoapp.data.network

import com.nagarro.todoapp.models.ListItem
import retrofit2.Response
import retrofit2.http.GET

interface ToDoListApi{
    //Q: What is suspend, why it's required,  Tyoe casting is required and woud be multiple, how can we avoid
    @GET("/todos")
    suspend fun getTodoListData(): Response<List<ListItem>>
}