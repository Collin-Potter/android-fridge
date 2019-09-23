package com.example.android_fridge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_fridge.HomeViewPagerFragmentDirections
import com.example.android_fridge.R
import com.example.android_fridge.data.ItemAndFridgeStock
import com.example.android_fridge.databinding.ListItemStockBinding
import com.example.android_fridge.viewmodels.ItemAndFridgeStockViewModel
import java.text.FieldPosition

class FridgeStockAdapter :
    ListAdapter<ItemAndFridgeStock, FridgeStockAdapter.ViewHolder>(FridgeItemDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_stock, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { stock ->
            with(holder) {
                bind(stock)
            }
        }
    }

    class ViewHolder(
        private val binding: ListItemStockBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.viewModel?.itemId?.let { itemId ->
                    navigateToItem(itemId, view)
                }
            }
        }

        private fun navigateToItem(itemId: String, view: View) {
            val direction = HomeViewPagerFragmentDirections
                .actionViewPagerFragmentToItemDetailFragment(itemId)
            view.findNavController().navigate(direction)
        }

        fun bind(stock: ItemAndFridgeStock) {
            with(binding) {
                viewModel = ItemAndFridgeStockViewModel(stock)
                executePendingBindings()
            }
        }
    }

}

private class FridgeItemDiffCallback : DiffUtil.ItemCallback<ItemAndFridgeStock>() {

    override fun areItemsTheSame(
        oldItem: ItemAndFridgeStock,
        newItem: ItemAndFridgeStock
    ): Boolean {
        return oldItem.item.itemId == newItem.item.itemId
    }

    override fun areContentsTheSame(
        oldItem: ItemAndFridgeStock,
        newItem: ItemAndFridgeStock
    ): Boolean {
        return oldItem.item == newItem.item
    }
}