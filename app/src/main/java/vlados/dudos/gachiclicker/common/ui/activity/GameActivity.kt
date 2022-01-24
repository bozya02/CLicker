package vlados.dudos.gachiclicker.common.ui.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import vlados.dudos.gachiclicker.R
import vlados.dudos.gachiclicker.app.App
import vlados.dudos.gachiclicker.common.Case.clicks
import vlados.dudos.gachiclicker.common.Case.cumPerClick
import vlados.dudos.gachiclicker.common.Case.cumPerSecond
import vlados.dudos.gachiclicker.common.Case.currentCum
import vlados.dudos.gachiclicker.common.Case.cutNum
import vlados.dudos.gachiclicker.common.Case.saveData
import vlados.dudos.gachiclicker.common.Case.updateCurrentCum
import vlados.dudos.gachiclicker.common.ui.fragments.EventsFragment
import vlados.dudos.gachiclicker.common.ui.fragments.GameFragment
import vlados.dudos.gachiclicker.common.ui.fragments.SettingsFragment
import vlados.dudos.gachiclicker.common.ui.fragments.ShopFragment
import vlados.dudos.gachiclicker.databinding.ActivityGameBinding
import java.util.concurrent.TimeUnit

class GameActivity : AppCompatActivity() {

    private lateinit var b: ActivityGameBinding
    private lateinit var thread: Disposable
    private lateinit var mediaPlayer: MediaPlayer
    private val fireBaseFS = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        b = ActivityGameBinding.inflate(layoutInflater)
        getUserCum(App.dm.getUserMail())
        updateDate()
        fragmentTransaction(GameFragment())
        setContentView(b.root)

        b.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.click -> {
                    fragmentTransaction(GameFragment())
                    changeInfoVisibility(View.VISIBLE)
                }

                R.id.shop -> {
                    fragmentTransaction(ShopFragment())
                    changeInfoVisibility(View.VISIBLE)
                }
                R.id.events -> {
                    fragmentTransaction(EventsFragment())
                    changeInfoVisibility(View.GONE)
                }
                R.id.settings -> {
                    fragmentTransaction(SettingsFragment())
                    changeInfoVisibility(View.GONE)
                }

            }
            true
        }
    }

    override fun onStart() {
        super.onStart()
        mediaPlayer = MediaPlayer.create(this, R.raw.minecraft_rv)
        mediaPlayer.setVolume(0.07f, 0.07f)
        cpsThread()
    }

    override fun onStop() {
        super.onStop()
        saveData()
        mediaPlayer.pause()
        thread.dispose()
    }

    private fun fragmentTransaction(fmt: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_holder, fmt).addToBackStack(null)
            .commit()
    }
    fun updateDate(){
        b.yourCum.text = getString(R.string.current_cum) + cutNum(currentCum)
        b.textCps.text = getString(R.string.cps) + cumPerSecond
        b.yourCPC.text = getString(R.string.current_cpc) + cumPerClick
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (b.bottomNavigation.selectedItemId != R.id.click){
            b.bottomNavigation.selectedItemId = R.id.click
            fragmentTransaction(GameFragment())
        }
        else finishAffinity()
    }
    private fun cpsThread(){
        thread = Observable
            .interval(1, TimeUnit.SECONDS)
            .doOnNext {
                cpsSaving()
                musicPlaying()
            }
            .subscribe()
    }
    private fun cpsSaving(){
        clicks = 13
        updateCurrentCum(cumPerSecond)
        updateDate()
    }
    private fun changeInfoVisibility(int: Int)
    {
        b.constraintInfo.visibility = int
    }
    private fun musicPlaying(){
        if (!mediaPlayer.isPlaying){
            mediaPlayer.start()
        }
    }
    private fun getUserCum(str: String){
        fireBaseFS.collection("Users").document("user:$str")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val userData = task.result?.data
                    cumPerClick = userData?.get("cpc").toString().toInt()
                    cumPerSecond = userData?.get("cps").toString().toLong()
                    currentCum = userData?.get("currentCum").toString().toLong()
                }
            }
    }
}


