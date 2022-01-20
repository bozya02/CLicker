package vlados.dudos.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import vlados.dudos.myapplication.common.Case
import vlados.dudos.myapplication.common.Case.cumPerSecond
import vlados.dudos.myapplication.common.Case.updateCurrentCum
import vlados.dudos.myapplication.databinding.ActivityGameBinding
import vlados.dudos.myapplication.common.ui.fragments.EventsFragment
import vlados.dudos.myapplication.common.ui.fragments.GameFragment
import vlados.dudos.myapplication.common.ui.fragments.SettingsFragment
import vlados.dudos.myapplication.common.ui.fragments.ShopFragment
import java.util.concurrent.TimeUnit

class GameActivity : AppCompatActivity() {

    private lateinit var b: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        b = ActivityGameBinding.inflate(layoutInflater)
        updateDate()
        fragmentTransaction(GameFragment())
        setContentView(b.root)

        cpsThread()

        b.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.click -> {
                    fragmentTransaction(GameFragment())
                }

                R.id.shop -> {
                    fragmentTransaction(ShopFragment())
                }
                R.id.events -> {
                    fragmentTransaction(EventsFragment())
                }
                R.id.settings -> {
                    fragmentTransaction(SettingsFragment())
                }

            }
            true
        }


    }
    private fun fragmentTransaction(fmt: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_holder, fmt).addToBackStack(null)
            .commit()
    }
    fun updateDate(){
        b.textCps.text = getString(R.string.cps) + Case.cumPerSecond.toString()
        b.yourCum.text = getString(R.string.current_cum) + Case.currentCum.toString()
        b.yourCPC.text = getString(R.string.current_cpc) + Case.cumPerClick.toString()
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
        Observable
            .interval(1, TimeUnit.SECONDS)
            .doOnNext { n -> cpsSaving() }
            .subscribe()
    }
    private fun cpsSaving(){
        updateCurrentCum(cumPerSecond)
        updateDate()
    }
}


