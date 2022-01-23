package vlados.dudos.myapplication.data

import android.content.Context
import android.content.SharedPreferences

class DataManager(private val baseContext: Context) {
    private val shared: SharedPreferences

    init {
        shared = baseContext.getSharedPreferences("cum", Context.MODE_PRIVATE)
    }
    //get stats
    fun getCPS() : Long = shared.getLong("cps", 0)
    fun getCPC() : Int = shared.getInt("cpc", 1)
    fun getCurrentCum() : Long = shared.getLong("current", 0)
    fun getPriceCPC() : Long = shared.getLong("cpc_price", 250)
    fun getPriceDick() : Long = shared.getLong("dick", 500)
    fun getRikardoPrice() : Long = shared.getLong("rikardo", 15000)
    fun getCocktailPrice() : Long = shared.getLong("cocktail", 80000)

    //settings
    fun getBioState() : Boolean = shared.getBoolean("biometry", false)
    fun saveBiometryState(b: Boolean) = shared.edit().putBoolean("biometry", b).apply()
    fun getSoundState() : Boolean = shared.getBoolean("wewe", false)
    fun setSoundState(b: Boolean) = shared.edit().putBoolean("wewe", b).apply()

    //set stats
    fun setCPS(num: Long) = shared.edit().putLong("cps", num).apply()
    fun setCPC(num: Int) = shared.edit().putInt("cpc", num).apply()
    fun setCurrentCum(num: Long) = shared.edit().putLong("current", num).apply()

    //set price
    fun setPrice(str: String, num: Long) = shared.edit().putLong(str, num).apply()
}
