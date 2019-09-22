package com.example.android_fridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_fridge.databinding.FragmentFridgeBinding

class FridgeFragment : Fragment() {

    private lateinit var binding: FragmentFridgeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFridgeBinding.inflate(inflater, container, false)
        return binding.root
    }
}