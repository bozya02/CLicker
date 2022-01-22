package vlados.dudos.myapplication.common.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import vlados.dudos.myapplication.R
import vlados.dudos.myapplication.common.Case.clicks
import vlados.dudos.myapplication.common.Case.cumPerClick
import vlados.dudos.myapplication.common.Case.cumPerSecond
import vlados.dudos.myapplication.common.Case.currentCum
import vlados.dudos.myapplication.common.Case.cutNum
import vlados.dudos.myapplication.common.Case.saveData
import vlados.dudos.myapplication.common.Case.updateCurrentCum
import vlados.dudos.myapplication.common.ui.fragments.EventsFragment
import vlados.dudos.myapplication.common.ui.fragments.GameFragment
import vlados.dudos.myapplication.common.ui.fragments.SettingsFragment
import vlados.dudos.myapplication.common.ui.fragments.ShopFragment
import vlados.dudos.myapplication.databinding.ActivityGameBinding
import java.util.concurrent.TimeUnit

class GameActivity : AppCompatActivity() {

    private lateinit var b: ActivityGameBinding
    private lateinit var thread: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        b = ActivityGameBinding.inflate(layoutInflater)
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

        cpsThread()
    }

    override fun onStop() {
        super.onStop()
        saveData()
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
            .doOnNext { cpsSaving() }
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
}


