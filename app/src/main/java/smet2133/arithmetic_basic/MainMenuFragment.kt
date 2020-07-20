package smet2133.arithmetic_basic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainMenuFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_settings).setOnClickListener {
            findNavController().navigate(R.id.action_MainMenu_to_Settings)
        }

        view.findViewById<Button>(R.id.button_start).setOnClickListener {
            findNavController().navigate(R.id.action_MainMenu_to_Test)
        }

        view.findViewById<Button>(R.id.button_reports).setOnClickListener {
            findNavController().navigate(R.id.action_MainMenuFragment_to_reportsFragment)
        }
    }
}