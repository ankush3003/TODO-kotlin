package com.nagarro.todoapp.data.repository

import com.nagarro.todoapp.models.ListItem

interface IDataRepository {
    suspend fun getTodoList(): UseCaseResult<List<ListItem>>
}