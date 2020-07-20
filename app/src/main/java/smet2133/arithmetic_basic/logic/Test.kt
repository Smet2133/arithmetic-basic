package smet2133.arithmetic_basic.logic

import android.content.Context
import android.os.Build
import android.os.SystemClock
import androidx.annotation.RequiresApi
import org.threeten.bp.LocalDateTime;
import smet2133.arithmetic_basic.Arithmetic
import smet2133.arithmetic_basic.logic.entity.Mistake
import smet2133.arithmetic_basic.persistance.Util
import java.io.*
import kotlin.random.Random

class Test: Serializable {

    var settings: Settings = Settings()

    constructor(){
        println("############### Test class, in constructor")
        println(startTime)
    }

    var startTime: LocalDateTime = LocalDateTime.now()
    var duration: Long = 0

    var taskNum: Int = -1
    var taskCorrect: Int = 0

    var firstArg: Int = 0
    var secondArg: Int = 0
    lateinit var question: String
    lateinit var corrAnsw: String

/*    var question = "$firstArg + $secondArg"
    var corrAnsw: String = (firstArg + secondArg).toString()*/

    var noMistake: Boolean = true
    var mistakes: MutableList<Mistake> = ArrayList()

    fun nextTask(){
        if (taskNum > 0) {
            if (noMistake) taskCorrect++
        }
        taskNum++
        noMistake = true

        firstArg = genArg()
        Thread.sleep(2)
        secondArg = genArg()

        question = "$firstArg + $secondArg"
        corrAnsw = (firstArg + secondArg).toString()
    }

    fun genArg(): Int {
        return Random(SystemClock.elapsedRealtime()).nextInt(settings.sumFrom, settings.sumTo)
    }

    fun checkResult(answ: String): Boolean {
        println("corrr answ: $corrAnsw")
        return if (!answ.equals(corrAnsw)) {
            mistakes.add(Mistake(question, corrAnsw, answ))
            noMistake = false
            false
        } else {
            true
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun saveTestToFile() {
        var tests: MutableList<Test>? =  Util.loadReports()
        if (tests == null) {
            tests = ArrayList()
            println("tests is null when trying to save to it")
        }
        tests.add(this)

        //println(Arithmetic.getAppContext().dataDir)
        //println(File())
        val fos: FileOutputStream = Arithmetic.getAppContext().openFileOutput("reports", Context.MODE_PRIVATE)
        val os = ObjectOutputStream(fos)
        os.writeObject(tests)
        os.close()
        fos.close()
    }





    override fun toString(): String {
        return "at $startTime for $duration with $taskNum/$taskCorrect"
    }

}