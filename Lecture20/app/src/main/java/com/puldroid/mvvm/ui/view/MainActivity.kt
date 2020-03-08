package com.puldroid.mvvm.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.puldroid.mvvm.R
import com.puldroid.mvvm.data.models.Repositories
import com.puldroid.mvvm.ui.adapter.RepositoriesAdapter
import com.puldroid.mvvm.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val vm by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    val adapter = RepositoriesAdapter()
    val list = arrayListOf<Repositories>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repoRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
//        vm.fetchRepo()
        vm.repos.observe(this, Observer {
            if(it.isNotEmpty()){
                adapter.swapData(it)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("RES",list[0])
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val item = savedInstanceState.getSerializable("RES") as Repositories
        list.add(item)
        adapter.swapData(list)

    }


}
