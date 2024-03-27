package org.example

import kotlinx.coroutines.*

fun example6(): Unit = runBlocking {
    val job1 = async { apiCall1() }
    val job2 = async { apiCall2(job1.await()) }

    printWithThread( job2.await())
}

suspend fun apiCall1(): Int {
    delay(1000)
    return 1
}

suspend fun apiCall2(num: Int): Int {
    delay(1000)
    return 2 + num
}

fun example5(): Unit = runBlocking {
    val job = async {
        3 + 5
    }

    val eight = job.await()
    printWithThread(eight)
}

fun example4(): Unit = runBlocking {
    val job1 = launch {
        delay(1000)
        printWithThread("Job 1")
    }

    job1.join()

    val job2 = launch {
        delay(1000)
        printWithThread("Job 2")
    }


}

fun example3(): Unit = runBlocking {
    val job = launch {
        (1..5).forEach {
            printWithThread(it)
            delay(500)
        }
    }

    delay(1_000L)
    job.cancel()
}

fun example2(): Unit = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) {
        printWithThread("Hello launch")
    }

    delay(1_000L)
    job.start()
}

fun example1() {
    runBlocking {
        printWithThread("START")
        launch {
            delay(2_000L)
            printWithThread("LAUNCH END")
        }
    }

    printWithThread("END")
}