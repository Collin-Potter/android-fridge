package com.example.android_fridge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_fridge.HomeViewPagerFragmentDirections
import com.example.android_fridge.data.Item
import com.example.android_fridge.databinding.ListItemItemBinding

class ItemAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(ItemDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ItemViewHolder).bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            ListItemItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    class ItemViewHolder(
        private val binding: ListItemItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.item?.let {item ->
                    navigateToItem(item, it)
                }
            }
        }

        private fun navigateToItem(
            item: Item,
            it: View
        ) {
            val direction = HomeViewPagerFragmentDirections.actionViewPagerFragmentToItemDetailFragment(item.itemId)
            it.findNavController().navigate(direction)
        }

        fun bind(i: Item) {
            binding.apply {
                item = i
                executePendingBindings()
            }
        }
    }
}

private class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.itemId == newItem.itemId
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}