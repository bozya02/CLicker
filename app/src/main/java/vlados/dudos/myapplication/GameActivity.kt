package vlados.dudos.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import vlados.dudos.myapplication.databinding.ActivityGameBinding
import vlados.dudos.myapplication.fragments.EventsFragment
import vlados.dudos.myapplication.fragments.GameFragment
import vlados.dudos.myapplication.fragments.ShopFragment

class GameActivity : AppCompatActivity() {

    private lateinit var b: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentTransaction(GameFragment())

        b = ActivityGameBinding.inflate(layoutInflater)
        setContentView(b.root)

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
                    fragmentTransaction(EventsFragment())
                }

            }
            true
        }


    }
    private fun fragmentTransaction(fmt: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_holder, fmt).addToBackStack(null)
            .commit()
    }
}


