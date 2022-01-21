package vlados.dudos.myapplication.common

import vlados.dudos.myapplication.app.App
import vlados.dudos.myapplication.common.ui.models.ShopItem
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.properties.Delegates

object Case {


    private val letterList = listOf("", "K", "M", "B", "T", "q", "Q")
    var clicks: Int = 13

    var currentCum: Long = App.dm.getCurrentCum()
    var cumPerClick:Int = App.dm.getCPC()
    var cumPerSecond:Int = App.dm.getCPS()

    var dickPrice: Int = App.dm.getPriceDick()
    var rikardoPrice: Int = App.dm.getRikardoPrice()
    var cocktailPrice: Int = App.dm.getCocktailPrice()

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
        App.dm.setPrice("cpc_price", priceCPC)
        App.dm.setPrice("dick", dickPrice)
        App.dm.setPrice("rikardo", rikardoPrice)
        App.dm.setPrice("cocktail", cocktailPrice)
        App.dm.setCurrentCum(currentCum)
        App.dm.setCPC(cumPerClick)
        App.dm.setCPS(cumPerSecond)
    }
    fun cutNum(num: Long): String{
        val specialIndex = num.toString().length / 3 - if (num.toString().length % 3 == 0) 1 else 0
        val df = DecimalFormat("#.###")
        df.roundingMode = RoundingMode.HALF_DOWN
        return df.format(num/1000.0.pow(specialIndex)) + letterList[specialIndex]
    }
    var shopList: List<ShopItem> = listOf(ShopItem("", "Мassаж простаты", "+1 cum /клик", 0, priceCPC),
    ShopItem("https://www.seekpng.com/png/full/12-120961_up-arrow-png-picture-up-arrow-png.png", nameItem = "Улучшение DICK", "+2 cum /секунду", 2, dickPrice),
    ShopItem("https://cdn130.picsart.com/287487439000211.png", "Пофлексить с Рикардо", "+15 cum /секунду", 20, rikardoPrice),
    ShopItem("https://img.icons8.com/ios-filled/344/bar.png", "Start celebrating", "+50 cum /секунду", 50, 150000))
}