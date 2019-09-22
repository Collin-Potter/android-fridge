package com.example.android_fridge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.example.android_fridge.databinding.ActivityFridgeBinding

class FridgeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityFridgeBinding>(this, R.layout.activity_fridge)
    }
}
