package smet2133.arithmetic_basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_test.*
import smet2133.arithmetic_basic.logic.Test
import smet2133.arithmetic_basic.persistance.Util
import java.io.FileInputStream
import java.io.ObjectInputStream
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ReportsFragment : Fragment() {

    lateinit var myView: View


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myView = view

        val textView: TextView = view.findViewById(R.id.textView_try) as TextView
       // var textView =  view.findViewById<TextView>(R.id.textView_try)
        textView.text = ""
        var tests = Util.loadReports()
        if (tests != null) {
            for (test in tests) {
                textView.text = textView.text as String + test.toString() + "\n"
            }
        } else {
            println("tests is null in Reports")
        }


        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            findNavController().navigate(R.id.action_Reports_to_MainMenu)
        }

/*

        var chronometer = view.findViewById<Chronometer>(R.id.chronometer)

        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            chronometer!!.stop()
            //test.setTime(chronometer.base)
            println(chronometer.base)
            println(SystemClock.elapsedRealtime() - chronometer.base)
            test.duration = SystemClock.elapsedRealtime() - chronometer.base
            test.save()
            findNavController().navigate(R.id.action_Test_to_MainMenu)
        }

        view.findViewById<Button>(R.id.button_test_next).setOnClickListener {
            var answ = view.findViewById<EditText>(R.id.plainText_answer).text
            println("########answ: $answ")
            if (!test.checkResult(answ.toString())) {
                view.findViewById<TextView>(R.id.textView_correctOrNot).setTextColor(Color.parseColor("red"))
                view.findViewById<TextView>(R.id.textView_correctOrNot).text = "Неверный ответ, попробуй ещё"
            } else {
                view.findViewById<TextView>(R.id.textView_correctOrNot).setTextColor(Color.parseColor("green"))
                view.findViewById<TextView>(R.id.textView_correctOrNot).text = "Молодец, продолжай решать"
                genNewTask()
            }
        }



        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()

        genNewTask()


*/

    }




}