package vlados.dudos.myapplication.fragments

import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vlados.dudos.myapplication.Case.cumPerClick
import vlados.dudos.myapplication.Case.cumPerSecond
import vlados.dudos.myapplication.Case.currentCum
import vlados.dudos.myapplication.Case.updateCurrentCum
import vlados.dudos.myapplication.GameActivity
import vlados.dudos.myapplication.R
import vlados.dudos.myapplication.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var soundPool: SoundPool
    private lateinit var b: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentGameBinding.bind(
            inflater.inflate(
                R.layout.fragment_game,container, false
            )
        )
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        soundPool = SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        soundPool.load(context, R.raw.wewe, 1)
        onClick()
    }

    private fun onClick(){
        b.btnCumClick.setOnClickListener {
            makeSound()
            updateCurrentCum(cumPerClick)
            (activity as GameActivity).updateDate()
        }
    }

    private fun makeSound(){
        soundPool.play(1,1f,1f,0,0,1f)
    }
}