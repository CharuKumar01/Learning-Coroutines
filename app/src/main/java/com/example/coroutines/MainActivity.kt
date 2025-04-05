package com.example.coroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        CoroutineScope(Dispatchers.IO).launch {
//            printFollowers()
//            val fbFollowers = getFbFollowers()
//            Log.d("check", "${fbFollowers.toString()} inside onCreate function")
//        }

        val tvInstagram = bind.tvInstagram
        val tvFacebook = bind.tvFacebook
        val btnFetch = bind.btnFetch

        val job = CoroutineScope(Dispatchers.IO).async {
            val fbFollowers = async { getFbFollowers() }
            val getInstagramFollowers = async { getInstagramFollowers() }

            Log.d("check", "Facebook -> ${fbFollowers.await()} & Instagram -> ${getInstagramFollowers.await()}")
        }
    }
    //here we goooooooooooooooooo!

//    private suspend fun printFollowers() {
//    }

    private suspend fun getFbFollowers(): Int {
        delay(1000)
        return 54
    }

    private suspend fun getInstagramFollowers(): Int {
        delay(1000)
        return 108
    }
}