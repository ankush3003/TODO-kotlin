package com.nagarro.todoapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nagarro.todoapp.data.repository.DataRepositoryImpl
import com.nagarro.todoapp.data.repository.IDataRepository
import com.nagarro.todoapp.ui.mainlist.MainListViewModel
import com.nagarro.todoapp.utils.MockResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class MainListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    lateinit var mockWebServer: MockWebServer

    @Mock
    private lateinit var dataRepository: IDataRepository

    lateinit var mainListViewModel: MainListViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainListViewModel = MainListViewModel(dataRepository = dataRepository)

        mockWebServer = MockWebServer()
        startMockWebserver()
    }

    @Test
    fun fetchToDoListFromServer() {
        Dispatchers.setMain(Dispatchers.IO)
        mockWebServer.enqueue(
            MockResponse.createMockResponse(
                "todo_list_success",
                HttpURLConnection.HTTP_OK
            )
        )
        mainListViewModel.reloadList()
        Thread.sleep(5000)

        Assert.assertTrue(mainListViewModel.listOfTodo.isNotEmpty())
        Dispatchers.resetMain()
    }

    /**
     * Method which starts the mockwebserver
     */
    private fun startMockWebserver() {
        mockWebServer.start(1234)
    }

    /**
     * Method which stops the mock webserver
     */
    @After
    fun stopMockWebserver() {

        mockWebServer.shutdown()
    }
}