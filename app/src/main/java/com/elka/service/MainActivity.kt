package com.elka.service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elka.service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }

  override fun onResume() {
    super.onResume()
    binding.service.setOnClickListener { startServiceMethod() }
  }

  private fun startServiceMethod() {
    startService(MyService.getIntent(this, 154))
  }
}