package com.codelabs.mvvm_jokesapi.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.codelabs.mvvm_jokesapi.R
import com.codelabs.mvvm_jokesapi.database.DatabaseJoke
import com.codelabs.mvvm_jokesapi.databinding.RowJokeBinding

class JokesAdapter : RecyclerView.Adapter<JokesViewHolder>() {
    private var viewModelAdapter: JokesAdapter? = null

    var results: List<DatabaseJoke> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val withDataBinding: RowJokeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            JokesViewHolder.LAYOUT,
            parent,
            false
        )
        return JokesViewHolder(withDataBinding)
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
       holder.viewDataBinding.also{
           it.results = results[position]
       }
    }


}

class JokesViewHolder(val viewDataBinding: RowJokeBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.row_joke
    }
}

