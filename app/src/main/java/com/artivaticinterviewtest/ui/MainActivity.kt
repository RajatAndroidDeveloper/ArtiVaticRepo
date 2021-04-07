package com.artivaticinterviewtest.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.artivaticinterviewtest.R
import com.artivaticinterviewtest.APICall.ApiHelperImpl
import com.artivaticinterviewtest.APICall.RetrofitBuilder
import com.artivaticinterviewtest.base.ApiUserAdapter
import com.artivaticinterviewtest.model.RowsItem
import com.artivaticinterviewtest.utils.Status
import com.artivaticinterviewtest.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: SingleNetworkCallViewModel
    private lateinit var adapter: ApiUserAdapter
    private var dataList: ArrayList<RowsItem>  = ArrayList<RowsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        setupObserver()

        itemsswipetorefresh.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )
        itemsswipetorefresh.setColorSchemeColors(Color.WHITE)

        itemsswipetorefresh.setOnRefreshListener {
            dataList.clear()
            adapter.notifyDataSetChanged()
            viewModel.fetchUsers()
        }
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ApiUserAdapter(arrayListOf(), this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    //progressBar.visibility = View.GONE
                    itemsswipetorefresh.isRefreshing = false
                    it.data?.let { rowsData ->
                        renderList(rowsData.rows)
                    }
                    it.data?.let { rowsdata ->
                        supportActionBar?.title = rowsdata.title
                    }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    itemsswipetorefresh.isRefreshing = false
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    itemsswipetorefresh.isRefreshing = false
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(rowsItem: ArrayList<RowsItem?>?) {
        dataList.clear()
        dataList=rowsItem as ArrayList<RowsItem>
        adapter.addData(dataList)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))
        ).get(SingleNetworkCallViewModel::class.java)
    }
}
