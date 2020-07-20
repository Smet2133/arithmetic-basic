package smet2133.arithmetic_basic

import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import smet2133.arithmetic_basic.logic.Test

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TestFragment : Fragment() {

    val test = Test()
    lateinit var myView: View





    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myView = view

        var chronometer = view.findViewById<Chronometer>(R.id.chronometer)

        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            chronometer!!.stop()
            //test.setTime(chronometer.base)
            println(chronometer.base)
            println(SystemClock.elapsedRealtime() - chronometer.base)
            test.duration = SystemClock.elapsedRealtime() - chronometer.base
            test.saveTestToFile()
            findNavController().navigate(R.id.action_Test_to_MainMenu)
        }

        view.findViewById<Button>(R.id.button_test_next).setOnClickListener {

            var field1 = if (view.findViewById<EditText>(R.id.plainText_answer1).text.toString().equals("")) 0 else view.findViewById<EditText>(R.id.plainText_answer1).text.toString().toInt()
            var field0 = if (view.findViewById<EditText>(R.id.plainText_answer0).text.toString().equals("")) 0 else view.findViewById<EditText>(R.id.plainText_answer0).text.toString().toInt()

            var answ: Int = field1 * 10 + field0
            println("########answ: $answ")
            if (!test.checkResult(answ.toString())) {
                view.findViewById<TextView>(R.id.textView_correctOrNot).setTextColor(Color.parseColor("red"))
                view.findViewById<TextView>(R.id.textView_correctOrNot).text = "Неверный ответ, попробуй ещё"
            } else if (((test.firstArg % 10 + test.secondArg % 10) >= 10) && view.findViewById<TextView>(R.id.plainText_addition).text.toString() != "1") {
                view.findViewById<TextView>(R.id.textView_correctOrNot).setTextColor(Color.parseColor("black"))
                view.findViewById<TextView>(R.id.textView_correctOrNot).text = "Ответ верный, но как ты посчитал без единички для десятков?"
            } else {
                view.findViewById<TextView>(R.id.textView_correctOrNot).setTextColor(Color.parseColor("green"))
                view.findViewById<TextView>(R.id.textView_correctOrNot).text = "Молодец, продолжай решать"
                genNewTask()
            }
        }



        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()

        genNewTask()



    }

    private fun genNewTask() {

        test.nextTask()

        var numbSlashCorrect = (test.taskNum).toString() + "/" + test.taskCorrect.toString()
        myView.findViewById<TextView>(R.id.textView_numSlashCorr).text = numbSlashCorrect


        myView.findViewById<TextView>(R.id.textView_first_argument).text = test.firstArg.toString()
        myView.findViewById<TextView>(R.id.textView_second_argument).text = test.secondArg.toString()
        myView.findViewById<TextView>(R.id.plainText_answer1).text = ""
        myView.findViewById<TextView>(R.id.plainText_answer0).text = ""
        myView.findViewById<TextView>(R.id.plainText_addition).text = ""
    }


}