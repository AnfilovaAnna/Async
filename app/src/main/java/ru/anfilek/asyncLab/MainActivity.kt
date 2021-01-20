package ru.anfilek.asyncLab

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import ru.anfilek.asyncLab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var handlerThread: MyHandlerThread? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener { startHandlerThread() }
        binding.btnStop.setOnClickListener { stopHandlerThread() }
        binding.btnAsync.setOnClickListener { startGame() }
        binding.btnFreeze.setOnClickListener { freeze() }

    }

    private fun startHandlerThread() {
        handlerThread = MyHandlerThread()
        handlerThread?.start()
        handlerThread?.post()
    }

    private fun stopHandlerThread() {
        if(!handlerThread?.isInterrupted!!){
            handlerThread?.interrupt()
        }
    }

    private fun startGame() {
        testSharedResources()
    }

    private fun freeze() {
        Thread.sleep(9000)
    }

}


