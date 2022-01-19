package vlados.dudos.myapplication

import android.content.SharedPreferences
import vlados.dudos.myapplication.app.App
import vlados.dudos.myapplication.models.ShopItem

object Case {
    var currentCum: Int = App.dm.getCurrentCum()
    var cumPerClick:Int = App.dm.getCPC()
    var cumPerSecond:Int = App.dm.getCPS()

    private const val cpcCoef: Double = 1.4

    var priceCPC: Int = App.dm.getPriceCPC()

    fun updateCPS(num: Int){
        cumPerSecond+=num
        App.dm.setCPS(cumPerSecond)
    }
    fun updateCPC(num: Int){
        cumPerClick+=num
        App.dm.setCPC(cumPerClick)
        updatePriceCPC()
    }
    fun updateCurrentCum(num: Int){
        currentCum+=num
        App.dm.setCurrentCum(currentCum)
    }

    private fun updatePriceCPC(){
        priceCPC = (priceCPC * cpcCoef).toInt()
        App.dm.setPriceCPC(priceCPC)
    }
    var shopList: List<ShopItem> = listOf(ShopItem("", "Мassаж простаты", "+1 к cum за клик", 0, priceCPC))
}