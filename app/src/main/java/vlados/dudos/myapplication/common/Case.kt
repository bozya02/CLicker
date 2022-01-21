package vlados.dudos.myapplication.common

import vlados.dudos.myapplication.app.App
import vlados.dudos.myapplication.common.ui.models.ShopItem

object Case {
    var currentCum: Int = App.dm.getCurrentCum()
    var cumPerClick:Int = App.dm.getCPC()
    var cumPerSecond:Int = App.dm.getCPS()

    var dickPrice: Int = App.dm.getPriceDick()
    var rikardoPrice: Int = App.dm.getRikardoPrice()

    private const val cpcCoef: Double = 1.25
    private const val cpsCoef: Double = 1.4

    var priceCPC: Int = App.dm.getPriceCPC()

    fun updateCPS(num: Int){
        cumPerSecond +=num
    }
    fun updateCPC(num: Int){
        cumPerClick +=num
        updatePriceCPC()
    }
    fun updateCurrentCum(num: Int){
        currentCum +=num
    }

    private fun updatePriceCPC(){
        priceCPC = (priceCPC * cpcCoef).toInt()
    }

    fun updateDick(){
        dickPrice = (dickPrice * cpsCoef).toInt()
        shopList[1].price = dickPrice
    }

    fun updateRikardo(){
        rikardoPrice = (rikardoPrice * cpsCoef).toInt()
        shopList[2].price = rikardoPrice
    }
    fun saveData(){
        App.dm.setDickPrice(dickPrice)
        App.dm.setPriceCPC(priceCPC)
        App.dm.setCurrentCum(currentCum)
        App.dm.setCPC(cumPerClick)
        App.dm.setCPS(cumPerSecond)
        App.dm.setRikardoPrice(rikardoPrice)
    }
    var shopList: List<ShopItem> = listOf(ShopItem("", "Мassаж простаты", "+1 cum за клик", 0, priceCPC),
    ShopItem("https://www.seekpng.com/png/full/12-120961_up-arrow-png-picture-up-arrow-png.png", nameItem = "Улучшение DICK", "+5 cum в секунду", 5, dickPrice),
    ShopItem("https://cdn130.picsart.com/287487439000211.png", "Преисполниться Рикардо", "+20 cum в секунду", 20, rikardoPrice))
}