package ru.anfilek.asyncLab

import android.util.Log
import java.lang.Math.random
import java.lang.Thread.sleep
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

//@Volatile  private var i: Int = 0

fun testSharedResources() {
    val i : AtomicInteger = AtomicInteger(0)

    var thread2 = Thread()
    var thread1 = Thread()

    thread1 = thread {
        for (k in 0..100) {

            i.getAndAdd((1..5).random())

            if (i.toInt() >= 100){
                thread2.interrupt()
                Log.d("TAG", "First thread win")
                break
            }
            Log.d("TAG", "First thread $i")
            try{

                sleep(100)
            } catch (e: Exception){}

        }

    }

    thread2 = thread {
        for (k in 0..100) {

            i.getAndAdd((1..5).random())

            if (i.toInt() >= 100){
                thread2.interrupt()
                Log.d("TAG", "Second thread win")
                break

            }
            Log.d("TAG", "Second thread $i")
            try{

                sleep(100)
            } catch (e: Exception){}

        }
    }
    thread2.join()
    thread1.join()

    Log.d("TAG", "thread result: $i")
}
