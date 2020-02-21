package com.nagarro.todoapp.ui.mainlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nagarro.todoapp.R
import com.nagarro.todoapp.databinding.ActivityMainListBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainListActivity : AppCompatActivity() {

    private val todoListModel: MainListViewModel by viewModel()
    lateinit var mainListBinding: ActivityMainListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainListBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_list)
        mainListBinding.viewmodel = todoListModel

        todoListModel.reloadList()
    }
}
