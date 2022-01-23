package vlados.dudos.gachiclicker.common.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import vlados.dudos.gachiclicker.common.Case.shopList
import vlados.dudos.gachiclicker.common.ui.activity.GameActivity
import vlados.dudos.gachiclicker.R
import vlados.dudos.gachiclicker.common.ui.adapters.ShopAdapter
import vlados.dudos.gachiclicker.databinding.FragmentShopBinding
import vlados.dudos.gachiclicker.common.ui.models.ShopItem

class ShopFragment : Fragment(), ShopAdapter.OnClickListener {

    override fun click(data: ShopItem) {
        b.shopRv.adapter!!.notifyDataSetChanged()
        (activity as GameActivity).updateDate()
    }
    private lateinit var b: FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentShopBinding.bind(
            inflater.inflate(
                R.layout.fragment_shop,container, false
            )
        )
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println(shopList)
        b.shopRv.adapter = ShopAdapter(requireContext(), shopList, this)
        b.shopRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}