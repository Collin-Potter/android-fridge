package com.example.android_fridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.android_fridge.adapters.ItemAdapter
import com.example.android_fridge.databinding.FragmentItemListBinding
import com.example.android_fridge.utilities.InjectorUtils
import com.example.android_fridge.viewmodels.ItemListViewModel

class ItemListFragment : Fragment() {

    private val viewModel: ItemListViewModel by viewModels {
        InjectorUtils.provideItemListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = ItemAdapter()
        binding.itemList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: ItemAdapter) {
        viewModel.items.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }

//        val response = viewModel.response.getCompleted()

//        println(response)
    }

}