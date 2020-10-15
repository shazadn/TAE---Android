package com.codelabs.mvvm_jokesapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codelabs.mvvm_jokesapi.database.DatabaseJoke
import com.codelabs.mvvm_jokesapi.databinding.FragmentFirstBinding
import com.codelabs.mvvm_jokesapi.recycler_adapters.JokesAdapter
import com.codelabs.mvvm_jokesapi.viewmodel.JokeViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private var viewModelAdapter: JokesAdapter? = null

    private val viewModel: JokeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_first, container, false)
        val binding: FragmentFirstBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_first, container, false)

        binding.setLifecycleOwner (viewLifecycleOwner)

        binding.viewmodel = viewModel
        viewModelAdapter = JokesAdapter()
        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply{
            layoutManager = GridLayoutManager(context, 1)
            adapter = viewModelAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserverToGetData()
    }

    fun setUpObserverToGetData() {
        viewModel.jokeResults.observe(viewLifecycleOwner,
            Observer<List<DatabaseJoke>> { joke ->
               joke.apply {
                   viewModelAdapter?.results = joke

//                   Timber.i(joke.get(1).joke)
//                   Log.i("Jokes data: ", joke.get(0).joke)

               }
            })
    }
}