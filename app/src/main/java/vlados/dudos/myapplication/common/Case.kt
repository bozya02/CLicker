package vlados.dudos.myapplication.common

import vlados.dudos.myapplication.app.App
import vlados.dudos.myapplication.common.ui.models.ShopItem

object Case {
    var currentCum: Int = App.dm.getCurrentCum()
    var cumPerClick:Int = App.dm.getCPC()
    var cumPerSecond:Int = App.dm.getCPS()

    var dickPrice: Int = App.dm.getPriceDick()

    private const val cpcCoef: Double = 1.2
    private const val cpsCoef: Double = 1.3

    var priceCPC: Int = App.dm.getPriceCPC()

    fun updateCPS(num: Int){
        cumPerSecond +=num
        App.dm.setCPS(cumPerSecond)
    }
    fun updateCPC(num: Int){
        cumPerClick +=num
        App.dm.setCPC(cumPerClick)
        updatePriceCPC()
    }
    fun updateCurrentCum(num: Int){
        currentCum +=num
        App.dm.setCurrentCum(currentCum)
    }

    private fun updatePriceCPC(){
        priceCPC = (priceCPC * cpcCoef).toInt()
        App.dm.setPriceCPC(priceCPC)
    }

    fun updateDick(){
        dickPrice = (dickPrice * cpsCoef).toInt()
        App.dm.setDickPrice(dickPrice)
        shopList[1].price = dickPrice
    }

    var shopList: List<ShopItem> = listOf(ShopItem("", "Мassаж простаты", "+1 cum за клик", 0, priceCPC),
    ShopItem("https://www.seekpng.com/png/full/12-120961_up-arrow-png-picture-up-arrow-png.png", nameItem = "Улучшение DICK", "+5 cum в секунду", 5, dickPrice))
}