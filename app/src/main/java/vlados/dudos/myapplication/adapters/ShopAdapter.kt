package vlados.dudos.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import vlados.dudos.myapplication.Case
import vlados.dudos.myapplication.Case.cumPerSecond
import vlados.dudos.myapplication.Case.currentCum
import vlados.dudos.myapplication.Case.priceCPC
import vlados.dudos.myapplication.Case.updateCPC
import vlados.dudos.myapplication.Case.updateCPS
import vlados.dudos.myapplication.Case.updateCurrentCum
import vlados.dudos.myapplication.R
import vlados.dudos.myapplication.databinding.ShopItemBinding
import vlados.dudos.myapplication.models.ShopItem

class ShopAdapter(private val context: Context, private val shopList: List<ShopItem>) : RecyclerView.Adapter<ShopAdapter.ViewHolder>(){

    lateinit var b: ShopItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.shop_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        b = ShopItemBinding.bind(holder.itemView)

        Glide.with(b.imgShopItem)
            .load(shopList[position].img)
            .error(R.drawable.block)
            .into(b.imgShopItem)

        b.txtNameShopItem.text = shopList[position].nameItem
        b.txtDescShopItem.text = shopList[position].description
        b.priceTxt.text = shopList[position].price.toString() + " cum"

        b.buyTxt.setOnClickListener {
            when(position){
                0 -> {
                    if (currentCum >= shopList[position].price) {
                        updateCPC(1)
                        updateCurrentCum(-shopList[position].price)

                        b.priceTxt.text = priceCPC.toString() + " cum"
                    }
                    else Toast.makeText(context, "У Fucking Slave недостаточно cum", Toast.LENGTH_SHORT).show()
                }
                else -> updateCPS(shopList[position].cpsBuff)
            }
        }
    }

    override fun getItemCount(): Int = shopList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}