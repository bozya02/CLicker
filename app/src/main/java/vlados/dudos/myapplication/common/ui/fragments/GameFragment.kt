package vlados.dudos.myapplication.common.ui.fragments

import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vlados.dudos.myapplication.common.Case.cumPerClick
import vlados.dudos.myapplication.common.Case.updateCurrentCum
import vlados.dudos.myapplication.common.ui.activity.GameActivity
import vlados.dudos.myapplication.R
import vlados.dudos.myapplication.app.App
import vlados.dudos.myapplication.common.Case.clicks
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
                R.layout.fragment_game, container, false
            )
        )
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkSound()
        onClick()
    }

    private fun onClick() {
        b.btnCumClick.setOnClickListener {
            if (clicks > 0) {
                clicks--
                makeSound()
                updateCurrentCum(cumPerClick)
                (activity as GameActivity).updateDate()
            }
        }
    }

    private fun makeSound() {
        if (App.dm.getSoundState())
            soundPool.play(1, 1f, 1f, 0, 0, 1f)
    }

    private fun checkSound() {
        if (App.dm.getSoundState()) {
            soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
            soundPool.load(context, R.raw.wewe, 1)
        }
    }
}