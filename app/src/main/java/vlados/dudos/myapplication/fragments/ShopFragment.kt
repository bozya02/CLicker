package vlados.dudos.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import vlados.dudos.myapplication.Case.shopList
import vlados.dudos.myapplication.GameActivity
import vlados.dudos.myapplication.R
import vlados.dudos.myapplication.adapters.ShopAdapter
import vlados.dudos.myapplication.databinding.FragmentSettingsBinding
import vlados.dudos.myapplication.databinding.FragmentShopBinding
import vlados.dudos.myapplication.models.ShopItem

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