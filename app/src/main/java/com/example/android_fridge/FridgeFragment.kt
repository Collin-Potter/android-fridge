package com.example.android_fridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.example.android_fridge.adapters.FridgeStockAdapter
import com.example.android_fridge.adapters.ITEM_LIST_PAGE_INDEX
import com.example.android_fridge.databinding.FragmentFridgeBinding
import com.example.android_fridge.utilities.InjectorUtils
import com.example.android_fridge.viewmodels.FridgeStockListViewModel

class FridgeFragment : Fragment() {

    private lateinit var binding: FragmentFridgeBinding

    private val viewModel: FridgeStockListViewModel by viewModels {
        InjectorUtils.provideFridgeStockListViewModelFactory(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFridgeBinding.inflate(inflater, container, false)
        val adapter = FridgeStockAdapter()
        binding.fridgeList.adapter = adapter

        binding.addItem.setOnClickListener {
            navigateToItemListPage()
        }

        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: FridgeStockAdapter, binding: FragmentFridgeBinding) {
        viewModel.itemAndFridgeStock.observe(viewLifecycleOwner) { result ->
            binding.hasStock = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }

    private fun navigateToItemListPage() {
        requireActivity().findViewById<ViewPager2>(R.id.view_pager).currentItem = ITEM_LIST_PAGE_INDEX
    }
}