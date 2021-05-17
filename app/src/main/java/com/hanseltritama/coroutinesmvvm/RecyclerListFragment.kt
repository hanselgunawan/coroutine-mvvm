package com.hanseltritama.coroutinesmvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanseltritama.coroutinesmvvm.adapter.RecyclerViewAdapter
import com.hanseltritama.coroutinesmvvm.viewmodel.MainActivityViewModel

class RecyclerListFragment : Fragment() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    companion object {
        fun newInstance() = RecyclerListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recycler_list, container, false)
        initViewModel(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.makeApiCall()
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        recyclerViewAdapter  = RecyclerViewAdapter(this.context)
        recyclerView.adapter = recyclerViewAdapter
    }

    private fun setupObserver() {
        viewModel.recyclerListLiveData.observe(this, Observer { recyclerList ->
            if (recyclerList != null) {
                recyclerViewAdapter.setUpdatedData(recyclerList.items)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}