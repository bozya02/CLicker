package vlados.dudos.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vlados.dudos.myapplication.R
import vlados.dudos.myapplication.databinding.FragmentEventsBinding
import vlados.dudos.myapplication.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var b: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentSettingsBinding.bind(
            inflater.inflate(
                R.layout.fragment_events,container, false
            )
        )
        return b.root
    }
}