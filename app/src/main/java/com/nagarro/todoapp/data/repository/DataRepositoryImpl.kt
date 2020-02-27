package com.nagarro.todoapp.data.repository

import com.nagarro.todoapp.data.network.ToDoListApi
import com.nagarro.todoapp.models.ListItem

//Q: Why open, why private
open class DataRepositoryImpl(private val api: ToDoListApi) :
    IDataRepository {
    override suspend fun getTodoList(): UseCaseResult<List<ListItem>> {
        return try {
            val result = api.getTodoListData()
            if (result.isSuccessful)
                UseCaseResult.Success(result.body() as List<ListItem>)
            //Q: Type casting is required everywhere...how can we avoid this
            else UseCaseResult.Error(RuntimeException("test error"))
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }
}

