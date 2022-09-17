package com.example.wasim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // todo add feature launcher
    // @Inject
    // lateinit var launcher: TestRunLauncher
    private val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityScope.launch {
            delay(SPLASH_SCREEN_TIME)
            // launcher.launch(this@MainActivity)
            finish()
        }
    }

    override fun onDestroy() {
        activityScope.cancel()
        super.onDestroy()
    }

    companion object {
        private const val SPLASH_SCREEN_TIME = 2000L
    }

}
