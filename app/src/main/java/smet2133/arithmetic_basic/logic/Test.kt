package smet2133.arithmetic_basic.logic

import android.os.SystemClock
import android.widget.EditText
import kotlin.random.Random

class Test {

    var settings: Settings = Settings()

    constructor(){
        println("############### in constructor")
    }

    var taskNum: Int = 0
    var taskCorrect: Int = 0

    var firstArg: Int = 0
    var secondArg: Int = 0
    var mistake: Boolean = true

    fun nextTask(){
        if (!mistake) taskCorrect++
        taskNum++
        mistake = false
        firstArg = genArg()
        Thread.sleep(2)
        secondArg = genArg()
    }

    fun genArg(): Int {
        return Random(SystemClock.elapsedRealtime()).nextInt(settings.sumFrom, settings.sumTo)
    }

    fun checkResult(answ: String): Boolean {

        var corrAnsw = firstArg + secondArg
        println("corrr answ: $corrAnsw")
        return if (answ.equals(corrAnsw.toString())) {
            true
        } else {
            mistake = true
            false
        }
    }

}