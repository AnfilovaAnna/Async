package ru.anfilek.asyncLab

import android.util.Log
import java.lang.Thread.sleep
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun testSharedResources() {
    val i: AtomicInteger = AtomicInteger(0)

    var thread2 = Thread()
    var thread1 = Thread()

    thread1 = thread {
        while (i.toInt() <= 120) {

            i.getAndAdd(1)

            if (i.toInt() >= 120 && thread2.isAlive) {
                Log.d("TAG", "First thread win")
                thread2.interrupt()
                break
            } else {
                Log.d("TAG", "First thread $i")
            }
            try {
                sleep(100)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    thread2 = thread {
        while (i.toInt() <= 100) {

            i.getAndAdd(1)

            if (i.toInt() >= 100 && thread1.isAlive) {
                Log.d("TAG", "Second thread win")
                thread1.interrupt()
                break
            } else {
                Log.d("TAG", "Second thread $i")
            }


            try {
                sleep(100)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
    thread2.join()
    thread1.join()

}

