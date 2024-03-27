package org.example

import kotlinx.coroutines.*

fun example43() : Unit = runBlocking {
    val job = launch {
        try {
            delay(1000)
        }catch (ex : CancellationException){

        }

        printWithThread("delay 취소되지 않았다.")
    }

    delay(100)
    printWithThread("취소 시작")
    job.cancel()
}

fun example42(): Unit = runBlocking {
    val job = launch(Dispatchers.Default) {
        var i = 1
        var nextPrintTime = System.currentTimeMillis()
        while (i <= 5) {
            if (nextPrintTime <= System.currentTimeMillis()) {
                printWithThread("${i++}번째 출력")
                nextPrintTime += 1000L
            }

            if(!isActive){
                throw CancellationException()
            }
        }
    }

    delay(100)
    job.cancel()
}

private fun example41(): Unit = runBlocking {
    val job1 = launch {
        delay(1000)
        printWithThread("Job1")
    }

    val job2 = launch {
        delay(1000)
        printWithThread("Job2")
    }

    delay(100)
    job1.cancel()
}