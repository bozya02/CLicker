package vlados.dudos.myapplication.app

import android.app.Application
import vlados.dudos.myapplication.data.DataManager

class App : Application() {
    companion object {
        lateinit var dm: DataManager
    }
    override fun onCreate() {
        super.onCreate()
        dm = DataManager(baseContext)
    }
}