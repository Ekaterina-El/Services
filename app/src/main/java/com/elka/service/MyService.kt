package com.elka.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyService: Service() {
  private val scope = CoroutineScope(Dispatchers.Main)

  override fun onCreate() {
    super.onCreate()
    log("onCreate")
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    val start = intent?.getIntExtra(START_KEY, 0) ?: 0
    scope.launch {
      for (i in start..start + 100) {
        delay(1000)
        log("Timer $i")
      }
    }
    return START_NOT_STICKY
  }

  override fun onDestroy() {
    super.onDestroy()
    scope.cancel()
    log("onDestroy")
  }

  override fun onBind(p0: Intent?): IBinder? { TODO("Not yet implemented") }

  private fun log(message: String) {
    Log.d("SERVICE_TAG", "MyService: $message")
  }

  companion object {
    private const val START_KEY = "start_key"
    fun getIntent(context: Context, start: Int): Intent {
      return Intent(context, MyService::class.java).apply {
        putExtra(START_KEY, start)
      }
    }
  }
}