package com.example.android_fridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.android_fridge.databinding.FragmentViewPagerBinding
import com.example.android_fridge.adapters.FridgePagerAdapter
import com.example.android_fridge.adapters.ITEM_LIST_PAGE_INDEX
import com.example.android_fridge.adapters.MY_FRIDGE_PAGE_INDEX
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.IndexOutOfBoundsException

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = FridgePagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_FRIDGE_PAGE_INDEX -> R.drawable.fridge_tab_selector
            ITEM_LIST_PAGE_INDEX -> R.drawable.item_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_FRIDGE_PAGE_INDEX -> "My Fridge"
            ITEM_LIST_PAGE_INDEX -> "Item List"
            else -> null
        }
    }
}