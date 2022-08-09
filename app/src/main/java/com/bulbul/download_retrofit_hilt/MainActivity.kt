package com.bulbul.download_retrofit_hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bulbul.download_retrofit_hilt.databinding.ActivityMainBinding
import com.bulbul.download_retrofit_hilt.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.isIndeterminate = false
        viewModel.download()

        lifecycleScope.launch {
            viewModel.getProgress.collect {
                Log.d("downloadApi", "onCreateeeee: $it")
                progressBar.progress= it
            }
        }


    }
}