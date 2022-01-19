package vlados.dudos.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vlados.dudos.myapplication.R
import vlados.dudos.myapplication.databinding.FragmentEventsBinding

class EventsFragment : Fragment() {

    private lateinit var b: FragmentEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentEventsBinding.bind(
            inflater.inflate(
                R.layout.fragment_events,container, false
            )
        )
        return b.root
    }
}