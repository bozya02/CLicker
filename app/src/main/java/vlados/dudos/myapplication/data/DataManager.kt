package vlados.dudos.myapplication.data

import android.content.Context
import android.content.SharedPreferences

class DataManager(private val baseContext: Context) {
    private val shared: SharedPreferences

    init {
        shared = baseContext.getSharedPreferences("cum", Context.MODE_PRIVATE)
    }
    //get stats
    fun getCPS() : Int = shared.getInt("cps", 0)
    fun getCPC() : Int = shared.getInt("cpc", 1)
    fun getCurrentCum() : Long = shared.getLong("current", 0)
    fun getPriceCPC() : Int = shared.getInt("cpc_price", 250)
    fun getPriceDick() : Int = shared.getInt("dick", 1000)
    fun getRikardoPrice() : Int = shared.getInt("rikardo", 50000)
    fun getCocktailPrice() : Int = shared.getInt("cocktail", 150000)

    //settings
    fun getBioState() : Boolean = shared.getBoolean("biometry", false)
    fun saveBiometryState(b: Boolean) = shared.edit().putBoolean("biometry", b).apply()
    fun getSoundState() : Boolean = shared.getBoolean("wewe", false)
    fun setSoundState(b: Boolean) = shared.edit().putBoolean("wewe", b).apply()

    //set stats
    fun setCPS(num: Int) = shared.edit().putInt("cps", num).apply()
    fun setCPC(num: Int) = shared.edit().putInt("cpc", num).apply()
    fun setCurrentCum(num: Long) = shared.edit().putLong("current", num).apply()

    //set price
    fun setPrice(str: String, num: Int) = shared.edit().putInt(str, num).apply()
}
